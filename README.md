# TIL
> Today I Learned 2024.02.08





---

### 오늘 작업

- [설정 - jwt]
  
 ```yml:application.yml

//JWT 설정
def querydslDir = "$buildDir/generated/querydsl"

sourceSets {
	main.java.srcDirs += [ querydslDir ]
}

tasks.withType(JavaCompile) {
	options.getGeneratedSourceOutputDirectory().set(file(querydslDir))
}

clean.doLast {
	file(querydslDir).deleteDir()
}
```

 ```html:hello.html

<div>
 <p>Hello, LYNMP!</p>
</div>
```

- [공통,회원 엔티티]

 ```html:hello.html

<div>
 <p>Hello, LYNMP!</p>
</div>
```

### 오류
 > ERROR : 연동시 지원되지 않는 문자 집합
> - DB연동시 특정 테이블이 추가되지 않음
> - implementation group: 'com.oracle.ojdbc', name: 'orai18n', version: '19.3.0.0' //의존성 추가로 해결



### 작업소감
- [how to blow up a k8s cluster](k8s/how-to-blow-up-a-cluster.md)
