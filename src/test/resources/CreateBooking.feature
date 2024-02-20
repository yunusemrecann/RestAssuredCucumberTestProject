Feature: I can create a hotel reservation

  Scenario: The user can create a hotel reservation and delete the reservation.
    Given The user creates a new reservation.
    And The user provides the information required for the reservation.
    When The user creates a hotel reservation.
    Then The reservation has been created successfully.
    And The user cancels the created reservation.