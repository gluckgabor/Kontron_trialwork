Feature:

Scenario:2 Owners create and read test
	Given I add an Owner with following data and save it
	| firstName | lastName | address           | city   | telephone        |
	| Gabor      | Gluck    | 110 W. Liberty St.   | Madison | 6085551023 |
	When I refresh browser
	Then I see owner is loaded back correctly

Scenario:3 Owners update and read test
		Given I update an Owner to have following data: <firstName>, <lastName>, <address>, <city>, <telephone> and save it
			| firstName | lastName | address                | city      | telephone         |
			| Ana       | Horvat   | Trg bana Jelacica 7    | Split     | +385 98 765 4321  |
		When I refresh browser
		Then I see owner is loaded back correctly

Scenario:4 Owners delete and read test
		Given I delete an Owner
		When I refresh browser
		Then I see owner is correctly missing from returned results

