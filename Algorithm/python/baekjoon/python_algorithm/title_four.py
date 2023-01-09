# 조건문

money = False
car = True
if money:
    print("택시를 타고 가라")
elif not car:
    print()
else:
    print("걸어가라")


score = 70
if score >= 60:
    message = "success"
else:
    message = "failure"

# 조건부 표현식   (3항 연산자의 파이썬식, / else 꼭 있어야 한다.)
# 1. 성공일 때 조건을 먼저 써준다. / 2. 조건식을 써준다.
message = "success" if score >= 60 else "failure"
print(message)

# 반복문
i = 0
while i<10:
    i += 1
    print(" 현재 값 : %d" %i)

coffee = 10
money = 300
while money:
    print("돈을 받았으니 커피를 줍니다.")
    coffee -= 1
    print("남은 커피의 양은 %d개입니다."%coffee)
    if not coffee:
        print("커피가 다 떨어졌습니다.")
        break

# for 문
"""
for 변수 in 리스트(튜플, 문자열 등):
    수행할 문장1
    수행할 문장2
    ...
"""
test_list = ['one', 'two', 'three']
for i in test_list:
    print(i, end="")


sum = 0
for x in range(1, 11):
    sum = sum +x
print(sum)


# 이중 for문
result = []
for x in range(2, 10):
    for y in range(1, 10):
        result.append(x*y)

# 이중 for문 (리스트 내포)
result1 = [x * y for x in range(2, 10) for y in range(1, 10)]

