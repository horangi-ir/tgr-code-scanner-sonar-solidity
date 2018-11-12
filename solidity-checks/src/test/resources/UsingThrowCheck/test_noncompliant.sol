pragma solidity ^0.4.12;

contract test{
    address owner;
    
    function useSuperPowers(){ 
        if (msg.sender != owner) { throw; } // Noncompliant {{Use revert instead of throw.}}
    }
}
