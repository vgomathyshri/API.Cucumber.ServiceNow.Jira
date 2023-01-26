Feature: Change Request

Background:
Given setup baseuri
And validate cedentials

Scenario Outline: Create with file body
When create change request with file'<fileName>'
Then Status Code is 201
Examples:
|fileName|
|CreateChangeRequest1.json|
|CreateChangeRequest2.json|
