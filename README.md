# Statement-Derivation
This program takes in a function as a program argument, and then determines the right-most derivation of the statement based upon its grammar, displaying each step.   

The grammer for this argument is:                                           
Exp -> + Exp Exp | - Exp Exp | * Exp Exp | / Exp Exp | Literal  
Literal -> Var | Int  
Var -> a | b | ... | z  
Int -> 0 | 1 | ... | 9
