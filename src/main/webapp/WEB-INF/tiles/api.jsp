
<div class="container container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h1>API-Documentation</h1>
			<p class="api">For accessing the api a user-login with admin-rights is needed.</p>
			<h2>Handling User-Objects</h2>
			<div class="row">
				<div class="col-md-10 offset-md-2">
					<table class="table">
						<tr>
							<th>Object</th>
							<th>User</th>
						</tr>
						<tr>
							<td><span>Fields</span></td>
							<td>
							</td>
						</tr>
						<tr>
							<td>username</td>
							<td>String, Primary Key, NotNull, min=5, max=80</td>
						</tr>
						<tr>
							<td>firstname</td>
							<td>String, NotNull, min=5, max=80</td>
						</tr>
						<tr>
							<td>lastname</td>
							<td>String, NotNull, min=5, max=80</td>
						</tr>
						<tr>
							<td>password</td>
							<td>String NotNull,, min=5, max=100</td>
						</tr>
						<tr>
							<td>email</td>
							<td>String, NotNull, min=5, max=100, The password should be set in plain text. The backend takes care of encryption with BCrypt</td>
						</tr>
						<tr>
							<td>phone</td>
							<td>String, NotNull, Digits(integer=20,fraction=0)</td>
						</tr>
						<tr>
							<td>enabled</td>
							<td>boolean, hardcoded to true</td>
						</tr>
						<tr>
							<td>accountNonLocked</td>
							<td>boolean, hardcoded to true</td>
						</tr>
						<tr>
							<td>credentialsNonExpired</td>
							<td>boolean, hardcoded to true</td>
						</tr>
						<tr>
							<td>accountNonExpired</td>
							<td>boolean, hardcoded to true</td>
						</tr>
						<tr>
							<td>authority</td>
							<td>String, hardcoded to "ROLE_USER"</td>
						</tr>
						
					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Show all users</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/users</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>GET</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td><span>Code:</span> 200<br>
							<span>Content:</span>
							
							<pre>
[
	{
		"username": "Admin",
		"lastname": "Admin",
		"firstname": "Admin",
		"password": "$2a$10$dyYNLMB1l1FQeyOORqIXxub.t0HfNRMMTb4Yxt17qj8eNJnpzQPsW",
		"email": "admin@example.com",
		"authority": "ROLE_ADMIN",
		"phone": "01715648561",
		"enabled": true,
		"accountNonLocked": true,
		"credentialsNonExpired": true,
		"accountNonExpired": true,
		"authorities": [
			{
				"authority": "ROLE_ADMIN"
			}
		]
	}
]
							</pre></td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code: </span>401 UNAUTHORIZED<br>
							<span>Content:</span>
							<pre> 
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/users"
}
							</pre>


							</td>
						</tr>



					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Create a user</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/users</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>POST</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>User Object in JSON format<br>
								<span>Example:</span>
								<pre>
{   
	"username": "Testuser3", 
	"lastname": "Espen", 
	"firstname": "Dennis", 
	"email": "dennis.espen@hotmail.com", 
	"password": "dbpass", 
	"phone": "5551234"
}
								</pre>		
							</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td> <span>Code:</span> 201<br>
								<span>Content:</span>
								<pre>
{
	"username": "Testuser3",
	"lastname": "Espen",
	"firstname": "Dennis",
	"password": "$2a$10$Co0653ede8y6puttbM/wCOpDxt34jo8zqGJdqwDEJO1Mg5HjfPc0u",
	"email": "dennis.espen@hotmail.com",
	"authority": "ROLE_USER",
	"phone": "5551234",
	"enabled": true,
	"accountNonLocked": true,
	"credentialsNonExpired": true,
	"accountNonExpired": true,
	"authorities": [
	  {
	    "authority": "ROLE_USER"
	  }
	]
}
									</pre>
								</td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code:</span> 401 UNAUTHORIZED<br>
							<span>Reason:</span> Bad Credentials<br>
							<span>Content:</span>
								<pre>
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/users"
}
								</pre>
									<span>Code:</span> 500 Internal Server Error<br>
									<span>Reason:</span> Validation failed<br>
									<span>Content:</span>
								<pre>
{
	"timestamp": 1472236799700,
	"status": 500,
	"error": "Internal Server Error",
	"exception": "org.springframework.transaction.TransactionSystemException",
	"message": "Could not commit JPA transaction; nested exception is
				javax.persistence.RollbackException: Error while committing
				the transaction",
	"path": "/api/users"
}
								</pre>
Try to stay in the given constraints
							</td>
						</tr>



					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Update a user</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/users/{username}</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>PUT</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>User Object in JSON format<br>
								<span>Example:</span>
								<pre>
{   
	"username": "Testuser3", 
	"lastname": "Espen", 
	"firstname": "Dennis", 
	"email": "dennis.espen@hotmail.com", 
	"password": "dbpass", 
	"phone": "5551234"
}
								</pre>
							</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td><span>Code:</span> 200<br>
								<span>Content:</span>
								<pre>
{
	"username": "Testuser3",
	"lastname": "Espen",
	"firstname": "Dennis",
	"password": "$2a$10$Co0653ede8y6puttbM/wCOpDxt34jo8zqGJdqwDEJO1Mg5HjfPc0u",
	"email": "dennis.espen@hotmail.com",
	"authority": "ROLE_USER",
	"phone": "5551234",
	"enabled": true,
	"accountNonLocked": true,
	"credentialsNonExpired": true,
	"accountNonExpired": true,
	"authorities": [
	  {
	    "authority": "ROLE_USER"
	  }
	]
}
								</pre>
							</td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code:</span> 401 UNAUTHORIZED<br>
								<span>Reason:</span> Bad Credentials<br>
								<span>Content:</span>
								<pre>
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/users"
}
								</pre>
								Code: 500 Internal Server Error
								Reason: Validation failed
								Content:
								<pre>
{
	"timestamp": 1472236799700,
	"status": 500,
	"error": "Internal Server Error",
	"exception": "org.springframework.transaction.TransactionSystemException",
	"message": "Could not commit JPA transaction; nested exception is
		javax.persistence.RollbackException: Error while committing
		the transaction",
	"path": "/api/users"
}
								</pre>
Try to stay in the given constraints
							</td>
						</tr>



					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Delete a user</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/users/{username}</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>DELETE</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td><span>Code:</span> 204<br>
								<span>Content:</span> None</td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code:</span> 401 UNAUTHORIZED<br>
								<span>Reason:</span> Bad Credentials<br>
								<span>Content:</span>
								<pre>
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/users"
}
								</pre>
								<span>Code:</span> 500 Internal Server Error<br>
								<span>Reason:</span> User not found<br>
								<span>Content:</span>
								<pre>
{
	"timestamp": 1472237191154,
	"status": 500,
	"error": "Internal Server Error",
	"exception": "org.springframework.dao.InvalidDataAccessApiUsageException",
	"message": "The entity must not be null!; nested exception is
		java.lang.IllegalArgumentException: The entity must not be null!",
	"path": "/api/users/Testuser3"
}
								</pre>
							</td>
						</tr>
					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Get user by username</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/users/{username}</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>GET</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td><span>Code:</span> 200<br>
							<span>Content:</span>
							
							<pre>
[
	{
		"username": "Admin",
		"lastname": "Admin",
		"firstname": "Admin",
		"password": "$2a$10$dyYNLMB1l1FQeyOORqIXxub.t0HfNRMMTb4Yxt17qj8eNJnpzQPsW",
		"email": "admin@example.com",
		"authority": "ROLE_ADMIN",
		"phone": "01715648561",
		"enabled": true,
		"accountNonLocked": true,
		"credentialsNonExpired": true,
		"accountNonExpired": true,
		"authorities": [
			{
				"authority": "ROLE_ADMIN"
			}
		]
	}
]
							</pre></td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code: </span>401 UNAUTHORIZED<br>
							<span>Content:</span>
							<pre> 
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/users/{username}"
}
							</pre>


							</td>
						</tr>



					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h2>Handling Offer-Objects</h2>
			<div class="row">
				<div class="col-md-10 offset-md-2">
									<table class="table">
						<tr>
							<th>Object</th>
							<th>User</th>
						</tr>
						<tr>
							<td><span>Fields</span></td>
							<td>
							</td>
						</tr>
						<tr>
							<td>id</td>
							<td>int, Primary Key, AutoGenerated</td>
						</tr>
						<tr>
							<td>startingPoint/td>
							<td>String, NotNull, min=1, max=60</td>
						</tr>
						<tr>
							<td>destination</td>
							<td>String, NotNull, min=1, max=60</td>
						</tr>
						<tr>
							<td>username</td>
							<td>String, Foreign Key</td>
						</tr>
						<tr>
							<td>date</td>
							<td>Calendar, can be set and gotten by the date-property. Set with a string in the format: ""yyyy-MM-ddTHH:mm". For example: 2016-10-10T08:30</td>
						</tr>
						<tr>
							<td>price</td>
							<td>double, Digits(integer=6,fraction=2)</td>
						</tr>
						<tr>
							<td>dateObject</td>
							<td>String, can be set by passing a Calendar-Object. Gets a Calendar-Object.</td>
						</tr>
					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Show all offers</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/offers</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>GET</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td><span>Code:</span> 200<br>
								<span>Content:</span>
							
							<pre>
  {
    "id": 36,
    "startingPoint": "Paderborn",
    "destination": "München",
    "username": "espendennis",
    "date": "2016-08-19T13:45",
    "price": 23,
    "dateObject": 1471607100000
  },
							</pre></td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code:</span> 401 UNAUTHORIZED<br>
								<span>Content:</span>
							<pre> 
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/offers"
}
							</pre>


							</td>
						</tr>



					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Create an offer</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/users</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>POST</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>User Object in JSON format<br>
								<span>Example:</span>
								<pre>
{
	"startingPoint" : "Berlin",
	"destination": "München",
	"username":"espendennis",
	"price": 40,
	"date": "2016-10-11T13:45"
}
								</pre>		
							</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td><span>Code:</span> 201<br>
								<span>Content:</span>
								<pre>
{
  "id": 0,
  "startingPoint": "Berlin",
  "destination": "München",
  "username": "espendennis",
  "date": "2016-10-11T13:45",
  "price": 40,
  "dateObject": 1476186300000
}
									</pre>
								</td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code:</span> 401 UNAUTHORIZED<br>
								<span>Reason:</span> Bad Credentials<br>
								<span>Content:</span>
								<pre>
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/offers"
}
								</pre>
									<span>Code:</span> 500 Internal Server Error<br>
									<span>Reason:</span> Validation failed<br>
									<span>Content:</span>
								<pre>
{
	"timestamp": 1472236799700,
	"status": 500,
	"error": "Internal Server Error",
	"exception": "org.springframework.transaction.TransactionSystemException",
	"message": "Could not commit JPA transaction; nested exception is
				javax.persistence.RollbackException: Error while committing
				the transaction",
	"path": "/api/offers"
}
								</pre>
Try to stay in the given constraints
							</td>
						</tr>



					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Update an offer</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/users/{id}</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>PUT</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>Offer Object in JSON format<br>
								<span>Example:</span>
								<pre>
{ "startingPoint" : "Washington",
"destination": "New York",
"username":"POTUS",
"price": 50,
"date": "2017-08-04T12:00"
}
								</pre>
							</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td><span>Code:</span> 200<br>
								<span>Content:</span>
								<pre>
{
  "id": 64,
  "startingPoint": "Washington",
  "destination": "New York",
  "username": "POTUS",
  "date": "2017-08-04T12:00",
  "price": 50,
  "dateObject": 1501840800000
}
								</pre>
							</td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code:</span> 401 UNAUTHORIZED<br>
								<span>Reason:</span> Bad Credentials<br>
								<span>Content:</span>
								<pre>
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/offers"
}
								</pre>
									<span>Code:</span> 500 Internal Server Error<br>
									<span>Reason:</span> Validation failed<br>
									<span>Content:</span>
								<pre>
{
	"timestamp": 1472236799700,
	"status": 500,
	"error": "Internal Server Error",
	"exception": "org.springframework.transaction.TransactionSystemException",
	"message": "Could not commit JPA transaction; nested exception is
		javax.persistence.RollbackException: Error while committing
		the transaction",
	"path": "/api/offers"
}
								</pre>
Try to stay in the given constraints
							</td>
						</tr>



					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Delete an offer</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/users/{id}</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>DELETE</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td><span>Code:</span> 204<br>
								<span>Content:</span> None</td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code:</span> 401 UNAUTHORIZED<br>
								<span>Reason:</span> Bad Credentials<br>
								<span>Content:</span>
								<pre>
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/offers"
}
								</pre>
									<span>Code:</span> 500 Internal Server Error<br>
									<span>Reason:</span> Offer not found<br>
									<span>Content:</span>
								<pre>
{
	"timestamp": 1472237191154,
	"status": 500,
	"error": "Internal Server Error",
	"exception": "org.springframework.dao.InvalidDataAccessApiUsageException",
	"message": "The entity must not be null!; nested exception is
		java.lang.IllegalArgumentException: The entity must not be null!",
	"path": "/api/offers/94"
}
								</pre>
							</td>
						</tr>
					</table>
					<table class="table">
						<tr>
							<th>Title</th>
							<th>Get offer by id</th>
						</tr>
						<tr>
							<td>URL</td>
							<td>/api/offers{id}</td>
						</tr>
						<tr>
							<td>Method</td>
							<td>GET</td>
						</tr>
						<tr>
							<td>URL Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Data Params</td>
							<td>None</td>
						</tr>
						<tr>
							<td>Success Response</td>
							<td><span>Code:</span> 200<br>
								<span>Content:</span>
							
							<pre>
  {
    "id": 36,
    "startingPoint": "Paderborn",
    "destination": "München",
    "username": "espendennis",
    "date": "2016-08-19T13:45",
    "price": 23,
    "dateObject": 1471607100000
  },
							</pre></td>
						</tr>
						<tr>
							<td>Error Response</td>
							<td><span>Code:</span> 401 UNAUTHORIZED<br>
								<span>Content:</span>
							<pre> 
{
	"timestamp": 1472236261420,
	"status": 401,
	"error": "Unauthorized",
	"message": "Bad credentials",
	"path": "/api/offers/{id}"
}
							</pre>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
