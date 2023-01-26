Feature: Bug Management

Background:
Given setup baseuri
And validate cedentials

Scenario Outline: Create Bug with file body
When create bug with file'<fileName>'
Then Status Code is 201
Examples:
|fileName|
|CreateBug.json|

Scenario: Get a particular Bug
When get bug by bugId
Then Status Code is 200

Scenario Outline: Update Bug with file body
When Update Bug with file'<updateFileName>'
Then Status Code is 204
Examples:
|updateFileName|
|UpdateBug.json|

Scenario: Delete a Bug
When Delete a Bug
Then Status Code is 204
