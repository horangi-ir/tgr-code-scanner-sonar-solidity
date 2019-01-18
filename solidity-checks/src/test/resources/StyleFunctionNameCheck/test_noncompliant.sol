pragma solidity ^0.4.22;

contract StyleFunctionNameCheckContract {

    mapping (address => uint) private balances;

    address public owner;
    event LogDepositMade(address accountAddress, uint amount);

    function SimpleBank() public { // Noncompliant {{Function Name should use mixedCase.}}
        owner = msg.sender;
    }
}