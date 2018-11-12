pragma solidity ^0.4.22;
import "github.com/OpenZeppelin/zeppelin-solidity/contracts/math/SafeMath.sol";


contract Lucas {
   
    using SafeMath for uint; 
    using SafeMath for uint256;
    using SafeMath for int;
    
    uint256 result; 
    uint result1; 
    int result2; 

    uint8 result8; // Noncompliant {{Use the SafeMath(*) library that checks for overflows.}}
    uint16 result16; // Noncompliant {{Use the SafeMath(*) library that checks for overflows.}}
    uint32 result32; // Noncompliant {{Use the SafeMath(*) library that checks for overflows.}}
 
    function SafeAdd(uint256 a, uint256 b) {
      result = 0;
      result = a.add(b);
    }
}