from copy import copy
# bool
a = [1,2,3,4]
if a:
    print(a)

while a:
    print(a.pop())

# 변수
a = 1
b = "python"
c = [1,2,3,5]
d = c[:]
print(id(a))
print(id(b))
print(a is b)
print(c is d)
i = copy(c)
print(id(i))
print(id(c))

e, r = ('python', 'life',)
print(id(e))
print(id(r))

# 값 바꾸기
x = 1
y =2
x,y = y,x
print(x, y)