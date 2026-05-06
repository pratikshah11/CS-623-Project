'''
CS 623 - Final Project
Name - Pratik Shah
Transactions 3 and 4
'''

import psycopg2
from psycopg2 import extensions

def run_project_transactions():
    con = None
    try:
        # 1. Establish Connection 
        con = psycopg2.connect(
            host="localhost",
            database="Project",
            user="postgres",
            password="root"
        )

        # 2. Set Isolation Level to SERIALIZABLE (ACID: Isolation)
        con.set_isolation_level(extensions.ISOLATION_LEVEL_SERIALIZABLE)
        
        # 3. Disable autocommit to manually manage Atomicity
        con.autocommit = False
        cur = con.cursor()

        print("Starting Transactions...")

        # TRANSACTION 3: Update product p1 to pp1 
        # Use parameterized queries to prevent SQL Injection 
        sql3 = "UPDATE Product SET prodid = %s WHERE prodid = %s"
        cur.execute(sql3, ('pp1', 'p1'))
        print("Transaction 3: Product p1 updated to pp1.")

        # TRANSACTION 4: Update depot d1 to dd1 
        sql4 = "UPDATE Depot SET depid = %s WHERE depid = %s"
        cur.execute(sql4, ('dd1', 'd1'))
        print("Transaction 4: Depot d1 updated to dd1.")

        # 4. Commit changes (ACID: Durability/Atomicity) 
        con.commit()
        print("All transactions committed successfully.")

    except (Exception, psycopg2.DatabaseError) as err:
        # 5. Rollback on error (ACID: Atomicity) 
        print(f"Error: {err}")
        print("Transactions failed. Rolling back database to original state.")
        if con:
            con.rollback()

    finally:
        # 6. Cleanup 
        if con:
            cur.close()
            con.close()
            print("PostgreSQL connection is now closed.")

if __name__ == "__main__":
    run_project_transactions()