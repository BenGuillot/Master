import random
#longueur_cle = 56



IPtable = (58, 50, 42, 34, 26, 18, 10, 2,
           60, 52, 44, 36, 28, 20, 12, 4,
           62, 54, 46, 38, 30, 22, 14, 6,
           64, 56, 48, 40, 32, 24, 16, 8,
           57, 49, 41, 33, 25, 17, 9, 1,
           59, 51, 43, 35, 27, 19, 11, 3,
           61, 53, 45, 37, 29, 21, 13, 5,
           63, 55, 47, 39, 31, 23, 15, 7)

IPinvtable = (40, 8, 48, 16, 56, 24, 64, 32,
              39, 7, 47, 15, 55, 23, 63, 31,
              38, 6, 46, 14, 54, 22, 62, 30,
              37, 5, 45, 13, 53, 21, 61, 29,
              36, 4, 44, 12, 52, 20, 60, 28,
              35, 3, 43, 11, 51, 19, 59, 27,
              34, 2, 42, 10, 50, 18, 58, 26,
              33, 1, 41, 9, 49, 17, 57, 25)

Etable = (32, 1, 2, 3, 4, 5, 4, 5, 6, 7,
          8, 9, 8, 9, 10, 11, 12, 13, 12,
          13, 14, 15, 16, 17, 16, 17, 18,
          19, 20, 21, 20, 21, 22, 23, 24,
          25, 24, 25, 26, 27, 28, 29, 28, 29,
          30, 31, 32, 1)

PC1table = (57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4)


LStable = (1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1)

PC2table = (14, 17, 11, 24, 1, 5, 3, 28,
            15, 6, 21, 10, 23, 19, 12, 4,
            26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40,
            51, 45, 33, 48, 44, 49, 39, 56,
            34, 53, 46, 42, 50, 36, 29, 32)


Ptable = (16, 7, 20, 21, 29, 12, 28, 17,
          1, 15, 23, 26, 5, 18, 31, 10,
          2, 8, 24, 14, 32, 27, 3, 9,
          19, 13, 30, 6, 22, 11, 4, 25)

Pinvtable = (9, 17, 23, 31, 13, 28, 2, 18,
             24, 16, 30, 6, 26, 20, 10, 1,
             8, 14, 25, 3, 4, 29, 11, 19,
             32, 12, 22, 7, 5, 27, 15, 21)

S1=[[14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7],
    [0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8],
    [4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0],
    [15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13]]
S2=[[15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10],
    [3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5],
    [0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15],
    [13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9]]
S3=[[10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8],
    [13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1],
    [13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7],
    [1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12]]
S4=[[7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15],
    [13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9],
    [10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4],
    [3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14]]
S5=[[2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9],
    [14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6],
    [4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14],
    [11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3]]
S6=[[12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11],
    [10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8],
    [9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6],
    [4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13]]
S7=[[4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1],
    [13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6],
    [1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2],
    [6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12]]
S8=[[13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7],
    [1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2],
    [7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8],
    [2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11]]


R1table = [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
           17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 1]

R2table = [3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
           18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 1, 2]


def permutation(valeur, table):
    sortie = ""
    for i in table:
        sortie += valeur[i-1]
    return sortie


def ip(valeur):
    return permutation(valeur, IPtable)

def ipinv(valeur):
    return permutation(valeur, IPinvtable)



def S(i):
    if i == 1:
        return S1
    if i == 2:
        return S2
    if i == 3:
        return S3
    if i == 4:
        return S4
    if i == 5:
        return S5
    if i == 6:
        return S6
    if i == 7:
        return S7
    if i == 8:
        return S8

def xor(L, R):
    res = ""
    for i in range(len(L)):
        temp = int(L[i])^int(R[i])
        res += str(temp)
    return res

        
def string_convert(i):
    if i == 0 or i == '0':
        return "0000"
    if i == 1 or i == '1':
        return "0001"
    if i == 2 or i == '2':
        return "0010"
    if i == 3 or i == '3':
        return "0011"
    if i == 4 or i == '4':
        return "0100"
    if i == 5 or i == '5':
        return "0101"
    if i == 6 or i == '6':
        return "0110"
    if i == 7 or i == '7':
        return "0111"
    if i == 8 or i == '8':
        return "1000"
    if i == 9 or i == '9':
        return "1001"
    if i == 10 or i == 'A':
        return "1010"
    if i == 11 or i == 'B':
        return "1011"
    if i == 12 or i == 'C':
        return "1100"
    if i == 13 or i == 'D':
        return "1101"
    if i == 14 or i == 'E':
        return "1110"
    if i == 15 or i == 'F':
        return "1111"

def convertion(M):
    res = ''
    for i in M:
        res += string_convert(i)
    return res

def intersection(L, R):
    res = []
    for i in L:
        if i  in R:
            res += [i]
    return res


def Sbloc(M):
    res = ""
    for i in range(8):
        temp = M[6*i:6*(i+1)]
        ligne = int(temp[0]+temp[5], 2)
        col = int(temp[1:5], 2)
        res += string_convert(S(i+1)[ligne][col])
    return res

def f(M, K):
    E = permutation(M, Etable)
    temp = xor(E, K)
    sortie = Sbloc(temp)
    res = permutation(sortie, Ptable)
    return res


def generation_cle(cle):
    K = [permutation(cle, PC1table)]
    C = [K[0][:28]]
    D = [K[0][28:]]
    for i in range(1, 17):
        if LStable[i-1] == 1:
            C += [permutation(C[i-1], R1table)]
            D += [permutation(D[i-1], R1table)]
        else:
            C += [permutation(C[i-1], R2table)]
            D += [permutation(D[i-1], R2table)]
        K += [permutation(C[i] + D[i], PC2table)]
    return K

def DES(M, cle):
    temp = ip(M)
    L = temp[:32]
    R = temp[32:]
    K = generation_cle(cle)
    for i in range(15):
        L, R = R, xor(f(R, K[i+1]), L)
    L, R = xor(L, f(R, K[16])), R
    res = ipinv(L + R)
    return res




def erreur():
    res = ""
    for i in range(32):
        if random.uniform(0, 1) < 0.5:
            res += "1"
        else:
            res += "0"
    return res


def DES_erreur(M, cle):
    temp = ip(M)
    L = temp[:32]
    R = temp[32:]
    K = generation_cle(cle)
    err = erreur()
    for i in range(15):
        L, R = R, xor(f(R, K[i+1]), L)
    R = xor(R, err)
    L, R = xor(L, f(R, K[16])), R
    res = ipinv(L + R)
    return res


def Si(S, L):
    j = L[0] + L[5]
    j = int(j, 2)
    k = L[1:5]
    k = int(k, 2)
    res = string_convert(S[j][k])
    return res


def force_brut():
    L = ["000000", "000001", "000010", "000011", "000100", "000101", "000110", "000111",
         "001000", "001001", "001010", "001011", "001100", "001101", "001110", "001111",
         "010000", "010001", "010010", "010011", "010100", "010101", "010110", "010111",
         "011000", "011001", "011010", "011011", "011100", "011101", "011110", "011111",
         "100000", "100001", "100010", "100011", "100100", "100101", "100110", "100111",
         "101000", "101001", "101010", "101011", "101100", "101101", "101110", "101111",
         "110000", "110001", "110010", "110011", "110100", "110101", "110110", "110111",
         "111000", "111001", "111010", "111011", "111100", "111101", "111110", "111111"]
    return L


def recherche(C, Cfaux):
    C = ip(C)
    Cfaux = ip(Cfaux)
    R15 = C[32:]
    R15faux = Cfaux[32:]
    L16 = C[:32]
    L16faux = Cfaux[:32]
    Lxor = xor(L16, L16faux)
    res = [[], [], [], [], [], [], [], []]
    P_1 = permutation(Lxor, Pinvtable)
    E = permutation(R15, Etable)
    Efaux = permutation(R15faux, Etable)
    force = force_brut()
    for n in range(8):
        for i in range(64):
            K16 = force[i]
            prem = xor(E[6*n:6*(n+1)], K16)
            deux = xor(Efaux[6*n:6*(n+1)], K16)
            test = xor(Si(S(n+1), prem), Si(S(n+1), deux))
            if test == P_1[4*n:4*(n+1)]:
                res[n] += [force[i]]
    return res
            

def convert(i):
    res = bin(i)[2:]
    res = "0"*(8-len(res)) + res
    return res

def PC2inv(K):
    res=['x' for _ in range(56)]
    resultat = ""
    for i in range(len(PC2table)):
        res[PC2table[i]-1]=K[i]
    for i in range(len(res)):
        resultat += str(res[i])
    return resultat

def PC1inv(K):
    res=[0 for _ in range(64)]
    resultat = ""
    for i in range(len(PC1table)):
        res[PC1table[i]-1]=K[i]
    for i in range(8):
        temp = sum([int(res[j]) for j in range(i*8, i*8+1)])
        res[7+i*8] = temp%2
    for i in range(64):
        resultat += str(res[i])
    return resultat



def attaque(X, Y, Yx):
    test = []
    for i in range(len(Yx)):
        test += [recherche(Y, Yx[i])]  
    possible = [test[0][0], test[0][1], test[0][2], test[0][3], test[0][4], test[0][5], test[0][6], test[0][7]]
    for j in range(1, len(Yx)):
        for k in range(8):
            possible[k] = intersection(possible[k], test[j][k])
    nbop = len(possible[0])*len(possible[1])*len(possible[2])*len(possible[3])*len(possible[4])*len(possible[5])*len(possible[6])*len(possible[7])
    if nbop == 0:
        raise RuntimeError("Pas de clé.")
    res = []
    for K_1 in possible[0]:
        for K_2 in possible[1]:
            for K_3 in possible[2]:
                for K_4 in possible[3]:
                    for K_5 in possible[4]:
                        for K_6 in possible[5]:
                            for K_7 in possible[6]:
                                for K_8 in possible[7]:
                                    testK = K_1 + K_2 + K_3 + K_4 + K_5 + K_6 + K_7 + K_8
                                    print("K16 est égal à : ", hex(int(testK,2))[2:])
                                    testCD = PC2inv(testK)
                                    for i in range(256):
                                        temp = testCD
                                        C = convert(i)
                                        k = 0
                                        j = 0
                                        while j < 8 and k < len(testCD):
                                            if temp[k] == 'x':
                                                temp = temp[:k] + C[j] + temp[k+1:]
                                                j += 1
                                            k += 1
                                            
                                        testK = PC1inv(temp)
                                        if DES(M, testK) == Y:
                                            res += [testK]
                                    
    return res                            
                                            

#On rentre les données ici sous forme héxadécimale
M = "F7B9B623FBF71F68"
C = "1EF49F416DD5578A"
Yx = ["1CE59F456DD5578E",
"1EF69F016DD4578A",
"1EE49D416DD4578A",
"1EF49B077DD4578A",
"1FA49B457FD5578A",
"1FB49F416DD7578A",
"1EB49B416DD5558A",
"1FB49F403DD15788",
"16B49F407DC1578A",
"1EFC9F402DC1578A",
"1EF497406DC5578A",
"1EF48F482DD1578B",
"5EF49F402595578A",
"1EF48F416DDD578B",
"5EF49F416D955F8B",
"1EF48F41699557C3",
"7EF48F416D9547CB",
"1ED49F416DD546CA",
"1EF4BF4169D556CA",
"0AF49F6169D546CA",
"1AF4DF414DD547CA",
"1AF4DE416DF5578A",
"0AF49F416DD5778A",
"0AF4DF516CD557AA",
"8AF49F416CD5178A",
"1E749F416CD5138A",
"1EF41F516DD5138A",
"1EF59FD16DD5179E",
"1EF19F51ECD5139A",
"1EF59F416D55579E",
"1EF19F416DD5D78A",
"1EF09F456DD4571A" ]

#Transformation en binaire des données
M = convertion(M)
C = convertion(C)
for i in range(len(Yx)):
    Yx[i] = convertion(Yx[i])

test = attaque(M, C, Yx)
print("la clé est : ", hex(int(test[0],2))[2:])
