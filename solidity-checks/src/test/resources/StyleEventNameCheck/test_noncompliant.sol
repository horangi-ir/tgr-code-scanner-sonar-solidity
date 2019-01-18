pragma solidity ^0.4.22;

contract StyleEventNameCheckContract {

    mapping (address => uint) private balances;

    address public owner;
    event LogDepositMade(address accountAddress, uint amount); // Noncompliant {{Event Name should use mixedCase.}}

    function SimpleBank() public { 
        owner = msg.sender;
    }
}