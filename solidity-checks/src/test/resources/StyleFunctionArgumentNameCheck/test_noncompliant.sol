pragma solidity ^0.4.22;

contract StyleFunctionArgumentNameCheckContract {

    mapping (address => uint) private balances;

    address public owner;
    event LogDepositMade(address accountAddress, uint amount);

    function simpleBank(address AccountAddress) public { // Noncompliant {{Function Argument Name should use mixedCase.}}
        owner = msg.sender;
    }
}