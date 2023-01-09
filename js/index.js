console.log(  '로그에_출력할_값  ');
console.log({name: '홍길동', age: 20, married: false});

var container = Array(); 
for (var i=0; i<5; i++){ 
    container.push(function () { 
        console.log(`나는 ${i}번 입니다.`); 
    }); 
} 
container[4]();