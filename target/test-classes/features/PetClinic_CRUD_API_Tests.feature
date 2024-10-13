@CRUD
Feature: PetClinic BDD Keyword Driven API Tests

	@AddOwner
	Scenario:1 Owners create and read test
		Given I add an Owner with following data and save it
		| firstName | lastName | address           | city   | telephone        |
		| Gabor      | Gluck    | 110 W. Liberty St.   | Madison | 6085551023 |
		When I refresh browser
		Then I see owner is loaded back correctly

	@UpdateOwner
	Scenario:2 Owners update and read test
			Given I update an Owner to have following data and save it
				| firstName | lastName | address           | city    | telephone  |
				| GaborG     | GluckG    | 110 W. Liberty StG| MadisonG | 6085551020 |
			When I refresh browser
			Then I see owner is loaded back correctly after update

	@DeleteOwner
	Scenario:3 Owners delete and read test
			Given I delete an Owner
			When I refresh browser
			Then I see owner is correctly missing from returned results

