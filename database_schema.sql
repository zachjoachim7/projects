CREATE TABLE "Users" (
	"User_ID"	INTEGER,
	"Username"	TEXT,
	"Email"	TEXT,
	"PasswordHash"	TEXT
);

CREATE TABLE "Access_List" (
	"ACL_ID"	INTEGER,
	"User_ID"	INTEGER,
	"VDI_ID"	INTEGER
);

CREATE TABLE "VDIs" (
	"VDI_ID"	INTEGER,
	"SecAnonymousToken"	INTEGER
);
