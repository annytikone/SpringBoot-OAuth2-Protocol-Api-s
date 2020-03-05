# SpringBoot-OAuth2-Protocol-Api-s
This project is about Online Library Management System using OAUTH2 Authenticated Tokenization
I've built this project to understand how oauth2/token authentication protocols works using Java's Spring boot framework.,
I've also included JdbcTemplate inside of this project.
 here's an reference to my simple,mysql db->
 
 
 
 mysql> select * from book;
+----+-------------------+------------+----------------+-------+
| id | bookname          | booktype   | authorname     | price |
+----+-------------------+------------+----------------+-------+
|  1 | THE VINCHI CODE   | HISTORY    | SAGAR PALASKAR |    10 |
|  2 | THE VINCHI CODE 2 | HISTORY    | SAGAR PALASKAR |   100 |
|  6 | harrypotter       | jkrowlings | magic          |   500 |
+----+-------------------+------------+----------------+-------+
3 rows in set (0.02 sec)

mysql> select * from user;
+----+----------+----------+-----------+----------+-------+
| id | username | password | firstname | lastname | role  |
+----+----------+----------+-----------+----------+-------+
|  1 | ejaz     | 123      | ejaz      | momen    | USER  |
|  2 | sagar    | 123      | sagar     | P        | ADMIN |
|  4 | saggy    | 123      | sagar     | P        | ADMIN |
+----+----------+----------+-----------+----------+-------+
