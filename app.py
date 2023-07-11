from flask import Flask, render_template, request, redirect, session
import sqlite3

app = Flask(__name__)
app.secret_key = 'jskjdfkjsfljgsfkjvnskfljvnsljfvnaslkjvnfsklvjsanvlfkjnlsjdn'  # Change this to a secure value

# Database connection
DATABASE = 'name_of_database.db'

# Function to create the database tables if they don't exist
def create_tables():
    conn = sqlite3.connect(DATABASE)
    c = conn.cursor()
    c.execute("CREATE TABLE IF NOT EXISTS Users (User_ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT NOT NULL, Email TEXT NOT NULL, PasswordHash TEXT NOT NULL)")

    c.execute("CREATE TABLE IF NOT EXISTS Access_List (ACL_ID INTEGER PRIMARY KEY AUTOINCREMENT, User_ID INTEGER, VDI_ID INTEGER)")

    c.execute("CREATE TABLE IF NOT EXISTS VDIs (VDI_ID INTEGER PRIMARY KEY AUTOINCREMENT, SecAnonymousToken TEXT)")

    c.execute("INSERT INTO Users (User_ID, Username, Email, PasswordHash) VALUES (1, 'jdoe', 'jdoe@basic.com', 'password')")

    c.execute("INSERT INTO Users (User_ID, Username, Email, PasswordHash) VALUES (2, 'hstyles', 'hstyles@basic.com', 'password')")

    c.execute("INSERT INTO 'Access_List' (ACL_ID, User_ID, VDI_ID) VALUES (1, 1, 1)")

    conn.commit()
    conn.close()

# Route for the login page
@app.route('/', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['Username']
        password = request.form['PasswordHash']
        user_id = request.form['User_ID']
        email = request.form['Email']

        # Check if the provided credentials match the database
        conn = sqlite3.connect(DATABASE)
        c = conn.cursor()
        c.execute("SELECT * FROM Users WHERE Username = ? AND PasswordHash = ?", (username, password))
        user = c.fetchone()
        conn.close()

        if user is None:
            error = 'Invalid credentials. Please try again.'
            return render_template('login.html', error=error)
        else:
            # Store the username in the session for access control
            session['Username'] = user[1]
            return redirect('/dashboard')
    else:
        return render_template('login.html')

# Route for the dashboard page
@app.route('/dashboard')
def dashboard():
    # Check if the user is logged in
    if 'Username' in session:
        username = session['Username']
        user_id = ['User_ID']
        conn = sqlite3.connect(DATABASE)
        c = conn.cursor()
        c.execute("SELECT ACL_ID FROM Access_List WHERE User_ID = ?", (user_id,))
        access_level = c.fetchone()
        conn.close()

        # Check the user's access level to determine accessible buttons
        if access_level is None:
            error = 'Access level not defined for the user.'
            return render_template('dashboard.html', error=error)
        elif access_level[0] == 1:
            buttons = ['Button 1', 'Button 2']
        elif access_level[0] == 2:
            buttons = ['Button 3', 'Button 4']
        else:
            buttons = []

        return render_template('dashboard.html', username=username, buttons=buttons)
    else:
        return redirect('/')

# Route for adding users to the database
@app.route('/add_user', methods=['GET', 'POST'])
def add_user():
    if request.method == 'POST':
        username = request.form['Username']
        password = request.form['PasswordHash']
        email = request.form['Email']
        user_id = request.form['User_ID']

        # Insert the new user into the Users table
        conn = sqlite3.connect(DATABASE)
        c = conn.cursor()
        c.execute("INSERT INTO Users (User_ID, Username, Email, PasswordHash) VALUES (?, ?, ?, ?)", (user_id, username, email, password))
        conn.commit()
        conn.close()

        return redirect('/')
    else:
        return render_template('add_user.html')

if __name__ == '__main__':
    create_tables()
    app.run(debug=True)
