pragma solidity ^0.4.22;

contract LoopCheckContract {

    struct StudentStruct {
        uint grade;
    }

    mapping(address => StudentStruct) public studentStructs;
    address [] public studentList;

    event LogStudentGrade(address student, uint studentGrade);

    function appendStudentGrade(address student, uint studentGrade) public {
        studentList.push(student);
        studentStructs[student].grade = studentGrade;
    }

    function getStudentCount() public view returns(uint count) {
        return studentList.length;
    }

    function studentLoop() public {
        for (uint i=0; i<studentList.length; i++) { // Noncompliant {{The unbounded for loop is an anti-pattern.}}
            emit LogStudentGrade(studentList[i], studentStructs[studentList[i]].grade);
        }
    }

}