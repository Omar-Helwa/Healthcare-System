# Healthcare System

A Java 17 console application for managing healthcare records backed by MySQL. The project models doctors, patients, appointments, diagnoses, addresses, and hospital-management operations.

## Requirements

- Java 17 or newer
- MySQL 8 or compatible server
- MySQL Connector/J 9.0.0 (the project is configured for IntelliJ IDEA)
- A `healthcare` database with the tables expected by the repository's SQL queries

## Configuration

Database credentials are read from environment variables and are not stored in source code:

| Variable | Required | Default |
| --- | --- | --- |
| `HEALTHCARE_DB_URL` | No | `jdbc:mysql://localhost:3306/healthcare` |
| `HEALTHCARE_DB_USER` | Yes | None |
| `HEALTHCARE_DB_PASSWORD` | Yes | None |

PowerShell example:

```powershell
$env:HEALTHCARE_DB_USER = "healthcare_app"
$env:HEALTHCARE_DB_PASSWORD = "your-password"
$env:HEALTHCARE_DB_URL = "jdbc:mysql://localhost:3306/healthcare"
```

Use a dedicated MySQL application account with only the permissions this program needs. Do not use a root password in source code or commit local environment files.

## Running

Open the project in IntelliJ IDEA, configure the MySQL Connector/J library, set the environment variables above in the run configuration, and run `com.company.Main`.

The application loads and prints doctors and patients through `HospitalManagement`. Database connectivity is initialized by `DatabaseConnector`.

## Project structure

```text
src/com/company/     Application entry point
src/com/database/    MySQL connection and query handlers
src/com/functions/   Domain models and persistence operations
```

## Security notice

Any database password previously committed to this repository must be considered compromised. Change that password wherever it is used, revoke or remove the affected database account if appropriate, and rewrite Git history before republishing. Deleting a secret from the latest commit does not remove it from older commits.
