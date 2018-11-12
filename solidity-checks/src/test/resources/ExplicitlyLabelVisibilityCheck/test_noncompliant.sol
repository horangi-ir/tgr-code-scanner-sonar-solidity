pragma solidity 0.4.24;

contract test{
    uint256 epsilon = 1;  // Noncompliant {{Explicitly label the visibility.}}
    
    function foo(int a, int8 c){ // Noncompliant {{Explicitly label the visibility.}}

    }
}
