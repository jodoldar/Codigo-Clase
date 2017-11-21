# -*- coding: utf-8 -*-
# Nombre: Josep Vicent Dols
import sys
import random


def read_cnf_dimacs(filename):
    linenumber = 0
    num_variables = 0
    num_clauses = 0
    clauses = []
    try:
        with open(filename) as f:
            for line in f:
                linenumber += 1
                line = line.split()
                if len(line) == 0 or line[0] == 'c': continue
                if len(line) == 4 and line[0] == 'p' and line[1] == 'cnf':
                    num_variables = int(line[2])
                    num_clauses = int(line[3])
                    break;
                sys.exit("error reading cnf file '%s' at line %d" % (filename, linenumber))
            for line in f:
                linenumber += 1
                line = line.split()
                if len(line) == 0 or line[0] == 'c': continue
                clause = [int(x) for x in line]
                if clause[-1] != 0:
                    sys.exit("error reading cnf file '%s' at line %d expecting 0 at last position" \
                             % (filename, linenumber))
                del clause[-1]  # remove last element
                if any(abs(x) > num_variables for x in clause):
                    sys.exit("error reading cnf file '%s' at line %d variable out of range" \
                             % (filename, linenumber))
                clauses.append(clause)
    except ValueError:
        sys.exit("error reading cnf file '%s' at line %d parsing int" % (filename, linenumber))
    if len(clauses) != num_clauses:
        sys.exit("error reading cnf file '%s' number of clauses differ" % (filename,))
    # just in case, remove empty clauses
    clauses = [clause for clause in clauses if len(clauses) > 0]
    return num_variables, clauses


def choose_literal(clauses):
    smallest = min(len(clause) for clause in clauses)
    variables = set(y for clause in clauses for y in clause if len(clause) == smallest)
    # return random.choice(tuple(variables))
    return variables.pop()


def simplify(clauses, literal):
    if len(clauses) == 0: return True
    auxClauses = []
    notLiteral = literal * -1;
    #print("Lista inicial ", clauses)
    #print(literal)
    for clause in clauses:
        if len(clause) == 0: return False
        if ((literal not in clause) & (notLiteral not in clause)):
            auxClauses.append(clause)
        elif (notLiteral in clause):
            newClause = [x for x in clause if x != notLiteral]
            if len(newClause) == 0: return False
            auxClauses.append(newClause)
    #print("Lista devuelta ",auxClauses)
    # print(" ")
    if len(auxClauses) == 0: return True
    return auxClauses


def check(formula, listofliterals):
    # determines if the list of literals is able to assign a True value
    # to the formula
    for literal in listofliterals:
        formula = simplify(formula, literal)
        print("Despejando literal", literal)
        print(formula)
        if isinstance(formula, bool):
            return formula
    # at this point, the formula has not been fully simplified
    return False


def backtracking(formula):
    #print(formula)
    if len(formula) == 0: return True
    literal = choose_literal(formula)
    for choice in (literal, -literal):
        f = simplify(formula, choice)
        if f is not False:
            if f is True:
                return [choice]
            result2 = backtracking(f)
            if result2 is not None:
                result2.append(choice)
                return result2

    return None


def unit_propagation(clauses):
    #print("\tEstoy en unit_propagation")
    asignados = []
    auxClauses = list(clauses)
    unitaryClauses = [clause for clause in auxClauses if len(clause)==1]
    #print("\tClausulas unitarias: ",len(unitaryClauses), unitaryClauses)
    #while len(unitaryClauses)>0:
    for clause in unitaryClauses:
        #print("Clauses ",unitaryClauses)
        #clause = unitaryClauses[0]
        asignados.append(clause[0])

        ### Bloque extraido de la funcion simplify ###
        literal = clause[0]
        auxClausesLoc = []
        notLiteral = literal * -1;

        for part in auxClauses:
            if ((literal not in part) & (notLiteral not in part)):
                auxClausesLoc.append(part)
            elif (notLiteral in part):
                newClause = [x for x in part if x != notLiteral]
                auxClausesLoc.append(newClause)
                #print("New Clause: ", newClause)
                if len(newClause)==1:
                    unitaryClauses.append(newClause)
        #print(auxClausesLoc)
        if [] in auxClausesLoc:
            return False,[]
        elif len(auxClausesLoc)==0:
            return True,asignados
        auxClauses = list(auxClausesLoc)
        #print("Queda: ", auxClauses)
    #print("Devuelvo",auxClauses)
    return auxClauses,asignados


def pure_literal_elimination(clauses):
    #print("\tEstoy en pure_literal_elimination")
    #print("\tClause: ", clauses)
    flat_list = []
    auxClauses = []
    for sublist in clauses:
        for item in sublist:
            flat_list.append(item)
    numbers = list(set(flat_list))
    #print("\tNumeros: ",numbers)
    positives = [number for number in numbers if number>=0]
    #print("\tPositivos: ",positives)
    negatives = [number for number in numbers if number<0]
    #print("\tNegativos: ",negatives)
    puros = [number for number in numbers if (((number in positives) and (-1*number not in negatives))or((number in negatives) and (-1*number not in positives)))]
    #print("\tPuros: ",puros)
    for clause in clauses:
        salta = False
        #print(clause)
        for element in clause:
            #print(element)
            if element in puros:
                salta = True
                break
        #print(salta)
        if salta == False:
            auxClauses.append(clause)
        #print(auxClauses)
    if len(auxClauses)==0:
        #print("True: ", puros)
        return True,puros
    #print("Return ", auxClauses)
    return auxClauses,puros


def dpll(formula):
    result = []
    #print("Formula: ", formula)
    if len(formula) == 0: return None
    #print("Voy a unit_propagation")
    formula, asignados = unit_propagation(formula)
    #print("Asignados" ,asignados)
    if(formula==True):
        return asignados
    elif(formula==False):
        #print("FALSE")
        return None
    #print(formula)
    #print("Voy a pure_literal_elimination")
    formula, puros = pure_literal_elimination(formula)
    if(formula==True):
        #print("Acabo con puros", puros)
        return puros
    if(formula==False): return None
    #print(formula)
    #if len(formula) == 0: return None
    for elem in asignados:
        result.append(elem)
    for elem in puros:
        result.append(elem)
    if(formula==True):
        return result
    #print("Preasig",result)
    literal = choose_literal(formula)
    for choice in (literal, -literal):
        #print(choice)
        f = simplify(formula, choice)
        #print(f)
        if f is not False:
            if f is True:
                return [choice]
            result2 = dpll(f)
            if result2 is not None:
                #print("Result2 ", result2, "y ", result, "en choice ", choice)
                for elem in result2:
                    result.append(elem)
                result.append(choice)
                #print("Voy subiendo: ", result)
                #result2.append(choice)
                return result
    return None


######################################################################
######################       MAIN PROGRAM       ######################
######################################################################
if __name__ == "__main__":
    if len(sys.argv) != 2:
        print('\n%s dimacs_cnf_file\n' % (sys.argv[0],))
        sys.exit()

    file_name = sys.argv[1]
    #file_name = "aim-50-1_6-yes1-4.cnf"
    #file_name = "quinn.cnf"
    num_variables, clauses = read_cnf_dimacs(file_name)
    print(clauses)
    # replace backtracking by dpll when checking dpll
    resul = dpll(clauses)
    #resul = backtracking(clauses)
    print(resul)
    if resul != None:
        print("We have found a solution:", resul)
        print("The check returns:", check(clauses, resul))
    else:
        print("Not solution has been found")
