# CS 623 - Final Project Summary

## Team Members
- Pratik Shah
- Moldir Nurmakhan

## Project Overview
The project focuses on implementing ACID-compliant database transactions using **PostgreSQL**. The database schema involves three main relations:
- **Product** (`#prodid`, name, price)
- **Depot** (`#depid`, address, volume)
- **Stock** (`#prodid`, `#depid`, quantity)

## Technologies Used
- **PostgreSQL**: Relational database management.
- **Java**: Used to implement Transactions 1 and 2.
- **Python**: Used to implement Transactions 3 and 4.

## Implemented Transactions
We were tasked with ensuring ACID properties across the following database operations:
1. **Transaction 1** (Java): Delete product `p1` from `Product` and `Stock` tables.
2. **Transaction 2** (Java): Delete depot `d1` from `Depot` and `Stock` tables.
3. **Transaction 3** (Python): Update product `p1` to `pp1` in `Product` and `Stock` tables.
4. **Transaction 4** (Python): Update depot `d1` to `dd1` in `Depot` and `Stock` tables.

## Deliverables
- Source code hosted on individual GitHub repositories.
- A recorded video presentation demonstrating the execution of transactions.
