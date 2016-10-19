use mysql;
update user set authentication_string=password('1234') where user='root';
FLUSH PRIVILEGES;