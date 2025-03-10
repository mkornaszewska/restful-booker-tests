import pandas as pd
from tabulate import tabulate
import re
import os

# Function for natural sorting of test scenario IDs
def natural_sort_key(s):
    # Extract the numeric part from the test scenario ID
    if isinstance(s, str) and s.startswith('RB_TS_'):
        try:
            return int(re.search(r'RB_TS_(\d+)', s).group(1))
        except (AttributeError, ValueError):
            return 0
    return s

# File paths
test_scenarios_path = "restful_booker_test_scenarios.xlsm"
readme_path = "README.md"

# Read Excel file
df_test_scenarios = pd.read_excel(test_scenarios_path, dtype=str)

# Clean up data - replace newlines with HTML breaks
df_test_scenarios = df_test_scenarios.applymap(lambda x: str(x).replace("\n", "<br/>") if pd.notna(x) else "")

# Define priority levels
priority_levels = ["P0", "P1", "P2", "P3"]

# Define a custom sorting order for endpoints
def endpoint_sort_key(endpoint):
    # Define the order of HTTP methods
    method_order = {
        "GET": 1,
        "POST": 2,
        "PUT": 3,
        "PATCH": 4,
        "DELETE": 5
    }

    # Split the endpoint into method and path
    parts = endpoint.split(" ", 1)
    method = parts[0]
    path = parts[1] if len(parts) > 1 else ""

    # Handle special cases for paths
    if path == "/auth":
        path_order = 0  # Auth comes first
    elif path == "/booking":
        path_order = 1  # Collection endpoints come before specific resources
    elif path.startswith("/booking/"):
        path_order = 2  # Resource endpoints
    elif path == "/ping":
        path_order = 3  # Health check last
    else:
        path_order = 4  # Any other endpoints

    # Return a tuple for sorting (method order, path order, original path for alphabetical)
    return (path_order, method_order.get(method, 99), path)

# Get unique endpoints and sort them
endpoints = df_test_scenarios['API endpoint'].unique().tolist()
sorted_endpoints = sorted(endpoints, key=endpoint_sort_key)

print(f"Found {len(endpoints)} unique endpoints")
print(f"Sorted endpoints: {sorted_endpoints}")

# Read the README template
with open(readme_path, "r", encoding="utf-8") as f:
    readme_content = f.read()

# Process each endpoint
for endpoint in sorted_endpoints:
    print(f"Processing endpoint: {endpoint}")
    endpoint_df = df_test_scenarios[df_test_scenarios['API endpoint'] == endpoint]

    # Create a clean endpoint identifier for the comment markers
    endpoint_id = endpoint.replace(" ", "_").replace("/", "_").replace(":", "_").upper()

    # Process each priority level within this endpoint
    for priority in priority_levels:
        priority_df = endpoint_df[endpoint_df['Priority'] == priority]

        # Create the marker pattern for this endpoint and priority
        marker_pattern = f"<!-- {endpoint_id}_{priority}_TEST_SCENARIOS_START -->.*?<!-- {endpoint_id}_{priority}_TEST_SCENARIOS_END -->"

        if not priority_df.empty:
            print(f"  Found {len(priority_df)} {priority} scenarios for {endpoint}")

            # Sort by Test Scenario Id using natural sorting
            if 'Test Scenario Id' in priority_df.columns:
                # Apply natural sorting to ensure RB_TS_100 comes after RB_TS_99
                priority_df['sort_key'] = priority_df['Test Scenario Id'].apply(natural_sort_key)
                priority_df = priority_df.sort_values('sort_key')
                priority_df = priority_df.drop('sort_key', axis=1)  # Remove the temporary sort key column

            # Generate the markdown table
            table = tabulate(priority_df.to_numpy().tolist(),
                             headers=priority_df.columns.tolist(),
                             tablefmt="pipe")

            replacement = f"<!-- {endpoint_id}_{priority}_TEST_SCENARIOS_START -->\n\n{table}\n\n<!-- {endpoint_id}_{priority}_TEST_SCENARIOS_END -->"
        else:
            print(f"  No {priority} scenarios found for {endpoint}, adding N/A")
            replacement = f"<!-- {endpoint_id}_{priority}_TEST_SCENARIOS_START -->\n\nN/A - No test scenarios for this priority level\n\n<!-- {endpoint_id}_{priority}_TEST_SCENARIOS_END -->"

        # Replace the content between markers
        if re.search(marker_pattern, readme_content, re.DOTALL):
            print(f"    Updating {endpoint_id}_{priority} section")
            readme_content = re.sub(marker_pattern, replacement, readme_content, flags=re.DOTALL)
        else:
            # If markers don't exist, look for general priority markers
            general_marker_pattern = f"<!-- {priority}_TEST_SCENARIOS_START -->.*?<!-- {priority}_TEST_SCENARIOS_END -->"
            if re.search(general_marker_pattern, readme_content, re.DOTALL):
                print(f"    Updating general {priority} section (endpoint markers not found)")

                if not priority_df.empty:
                    table_content = table
                else:
                    table_content = "N/A - No test scenarios for this priority level"

                readme_content = re.sub(general_marker_pattern,
                                        f"<!-- {priority}_TEST_SCENARIOS_START -->\n\n{table_content}\n\n<!-- {priority}_TEST_SCENARIOS_END -->",
                                        readme_content, flags=re.DOTALL)
            else:
                print(f"    WARNING: No markers found for {endpoint_id}_{priority} or general {priority}")

# Write the updated content back to README
with open(readme_path, "w", encoding="utf-8") as f:
    f.write(readme_content)

print("✅ README.md updated with test scenarios organized by endpoint and priority!")