pragma solidity 0.4.16;

contract Token {
    mapping(address => uint) public balanceOf;
    uint public totalSupply;

    function deposit() public payable {
        balanceOf[msg.sender] += msg.value;
        totalSupply += msg.value;
        assert(this.balance == totalSupply); // Noncompliant {{Avoid checking for strict balance equality.}}
    }
}