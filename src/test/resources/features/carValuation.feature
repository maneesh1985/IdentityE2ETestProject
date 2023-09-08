Feature: check car Value with cazoo.co.uk

  Scenario Outline: User check different car values
    When I fetch and validate car registration number are as below:
      | Data     |
      | AD58 VNF |
      | BW57 BOW |
      | KT17DLX  |
      | SG18 HTN |
    Given User open browser and lunch URL

    # It is important to note that there is a mismatch/failure in car_input_v2.txt and car_output-v2.txt file. Car reg number BW57 BOW is not a valid reg-
    # number. Therefore I have not included in to testcase.

    And I check value of car reg number '<Car Reg>' roughly around '<Mileage>' with mileage as '<Value>'
    Examples:
      | Car Reg  | Mileage | Value   |
      | AD58 VNF | 55000   | £3,100   |
      | KT17DLX  | 55000   | £15,425 |
      | SG18 HTN | 55000   | £11,525 |




