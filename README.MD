# IDEA EDU Course ...

Implemented in the Java Core Track of hyperskill.org' JetBrain Academy.

Main purpose of doing this project, is to practice some numerical mathematics and apply many new core topics of
the Java Core track, which was at 85% applied core topics before project start.

## Technology / External Libraries

- POJO Java 18
- Lombok' annotation processors,
- Apache Log4j SLF4J API binding to Log4j 2 logging and
- Junit 5 with
- Mockito (mockito-inline) testing.

## Repository Contents

The sources of main project tasks (5 stages) and the unit, mockito, spring testing.

## Program description

Arbitrary systems of linear equations of any dimensions can now be solved using the Gauss algorithm. 
Input and output are taken form and written to file. (cf. the example formats under resources dir - esp. test-folder.)
The input file has the format:

> <#variables> <#equations> 
> 
> <matrix row 1> -> in double format white space separated elements (integer works too of course)
> 
> ...
> 
> <matrix row #equations>

If the system of linear equations is contradictory, "No solutions" is printed to out file, if it has a non-trivial kernel.
just "Infinitely many solutions" is output - otherwise the unique solution is printed line wise to the file.
Input and output paths must be specified by command line arguments.

Have fun!

## Project completion

[//]: # (Project was completed on xx.0d.22.)

## Progress

26.06.22 Project started.

26.06.22 Stage 1 completed - few line main-method only.

28.06.22 Stage 2 completed, solving 2x2 linear equations, implemented with CommandLineReader, Matrix classes,
2x2 implementation of determinant and inverse of a square matrix, some matrix vector arithmatics.

03.07.22 Stage 3 completed, unique solvable (!) systems of linear equations of arbitrary dimension are now solved,
by implementing the Gauss algorithm. Input and output are taken / written to file now. (cf. the example formats under resources dir.)

16.07.22 Stage 4 completed, arbitrary systems of linear equations of any dimensions can now be solved
using the Gauss algorithm. Input and output are taken / written to file. (cf. the example formats under resources dir - esp. test-folder.)
If the system of linear equations is contradictory, "No solutions" is printed to out file, if it has a non-trivial kernel.
just "Infinitely many solutions" is output - otherwise the unique solution is printed line wise to the file.
Input and output paths must be specified by command line arguments.