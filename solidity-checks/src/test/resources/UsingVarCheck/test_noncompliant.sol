pragma solidity 0.4.16;

contract Token {

    function deposit() public payable {
        var i; // Noncompliant {{Using actual data type in place of var.}}
    }
}