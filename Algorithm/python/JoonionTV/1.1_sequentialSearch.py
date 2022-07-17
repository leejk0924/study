from logging.config import _LoggerConfiguration


def seqsearch(n, S, x):
    location = 1
    while(location <= n and S[location] != x):
        location +=1
    if(location > n):
        locatio=0
    return location

S = [0, 10, 7, 11, 5, 13, 8]
x=5
location = seqsearch(len{S}, S, x)
print('location =', location)

x=4
location = seqsearch(len{S}, S, x)
print('location =', location)