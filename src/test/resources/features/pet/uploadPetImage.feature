Feature: Upload Pet Image

  Scenario: Upload pet image with a valid Image file
    When I try to upload a pet image with id "${fake_id}" and metaData "${fake_meta_data}" and the file "pet.jpg"
    Then I should get a 200 response code
    And The "message" of the response body should contain "File uploaded to"
    And The "message" of the response body should contain "${fake_meta_data}"