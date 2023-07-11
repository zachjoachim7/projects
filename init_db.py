import sqlite3

conn = sqlite3.connect('name_of_database.db')

with open('database_schema.sql') as f:
    conn.executescript(f.read())

cur = conn.cursor()

cur.execute("INSERT INTO Users (User_ID, Username, Email, PasswordHash) VALUES (1, 'jdoe', 'jdoe@basic.com', 'password')")

cur.execute("INSERT INTO Users (User_ID, Username, Email, PasswordHash) VALUES (2, 'hstyles', 'hstyles@basic.com', 'password')")

cur.execute("INSERT INTO 'Access_List' (ACL_ID, User_ID, VDI_ID) VALUES (1, 1, 1)")

conn.commit()
conn.close()
