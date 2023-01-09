# 리스트 : 값 변경 가능
a=[1,5,3]

a.extend([2,3])
print(a)

# 튜플 : 튜플은 값 고정
# 인덱싱, 슬라이싱은 가능
b=(1,2,3,4, 'a', 'b')
print(b *2)

# 딕셔너리
dic = {1: 'a'}
dic['name'] = '익명'
keys = dic.keys()

values = dic.values()

print(dic)

aa = {
    '0' : 'AA',
    '1' : 'BB',
    '2': 'CC'
}

cc = [k for k, v in aa.items() if v == 'CC']
print(cc)

for k, v in aa.items():
    print('키는 : ' + str(k))
    print('밸류는 : ' + str(v))

a.clear()   # 딕셔너리 지우기

print(aa.get(4, '없음'))
print(4 in aa)      # 존재 여부를 Bool 타입으로 리턴


# 집합 : 중복된 값을 가질 수 없다. 또한, 순서가 없다.
s1 = set([1,2,3])  # s1 = set = {1,2,3}
print(s1)

s2 = set([1,2,3,4,5,6])
s3 = set([4,5,6,7,8,9])
print(s2 & s3)
print(s2.intersection(s3))  # 교집합

print(s2 | s3)      # 합집합
print(s2.union(s3))

print(s2 - s3) # 차집합
print(s2.difference(s3))