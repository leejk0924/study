def exchange (S):
    n = len(S)
    for i in range(n-1):
        for j in range(i+1, n):
            if(S[i] > S[j]):
                S[i], S[j] = S[j], S[i]     # 파이썬 SWAP 방법
        print(S)

S=[-1, 10, 7, 11, 5, 13,8]
print('Befor = ', S)
exchange(S)
print('After=', S)


