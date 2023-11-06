Feature: HUB site launch and Domain Search Functionality

Scenario: Verify user is on homepage and DSF is completed
	Given I launch the browser and go to the URL 
	Then I should see the homepage title 
	When I add the search text and hit on search
	Then  I should see the available domains message
	When I click on Get the domain message
	Then I should able to see the availability message