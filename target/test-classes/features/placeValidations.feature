Feature: Validate Place API
	I want validate the Place API's


@Regression
Scenario:1 Verify if place being succesfully added using AddPlaceAPI
	Given add headres to the API with below details:
	| Content-Type 			| Accept |
	| application/json 	| */* 	 |
	And add query parameters to the API with below details:
	| key 				|
	| qaclick123 	|
	When pass with body of "AddPlace" API below details:
	| latitude 	 | longitude | name  | address   							 | side layout  | language  |
	| -31.388192 | 37.589062 | atest | Marathahalli, Bangalore | shop				  | India-IND |
	And the user calls "AddPlace" API with "POST" request
	Then validate the status code "200"
	And validation done with below details:
	| status | place_id  | reference | id 			|
	| OK 		 | $NotNull  | NotNull 	 | NotNull  |

Scenario:2 Owners create and read test
	Given I add an Owner with following data and save it
	| firstName | lastName | address           | city   | telephone        |
	| Gabor      | Gluck    | 110 W. Liberty St.   | Madison | 6085551023 |
	When I refresh browser
	Then I see owner is loaded back correctly


