## Class
### 클래스와 객체의 개념

- 클래스 : 객체를 만들기 위한 설계도[속성(특징)과 메서드(동작)의 집합]
- 객체 : 설계도로부터 만들어낸

```python
class 클래스이름:
		def 메서드이름(self):
				명령블록
class Monster:
		def say(self):
				print("나는 몬스터다")

# 호출하기
인스턴스=클래스이름()             goblin=Monster()
인스턴스.메서드()                goblin.say()
```

- 파이썬에서듣 자료형도 클래스이다
- Monster 클래스에 속성을 추가해보자

```python
class Monster:
    def __init__(self, health, attack, speed):
				self.health = health
				self.attack = attack
				self.speed = speed
		# 메서드 추가하기(체력 감소, 체력 가져오기)
		def decrease_health(self, num):
				self.health -= num
		def get_health(self):
				return self.health

goblin = Monster(800, 120, 300)
wolf = Monster(1500, 200, 350)

goblin.decrease_health(100)
print(goblin.get_health())
```

- `__init__` : 인스턴스를 만들때 반드시 호출되는 메서드(가장먼저 호출되는 메서드)
- `self` : 인스턴스 자기자신을 뜻함

### 상속

- 부모클래스 정의

```python
class Monster:                                 # 속성 : 이름, 체력, 공격력
		def __init__(self, name, health, attack):  # 메서드 : 이동하기
				self.name = name
				self.health= health
				self.attack= attack
		def move(self):
				print("지상 이동")
```

- 자식클래스 정의

```python
class Wolf(Monster):         # 속성(Monster로부터 상속):이름, 체력, 공격력
		pass                     # (특정 코드 구현을 넘어가고 싶을때(정의만 하고 싶을 때) 
class Shark(Monster):
		def move(self):
				print("헤엄치기")     # 메서드 오버라이딩 : 메서드 재정의
class Dragon(Monster):       # 헤엄치기, 날기
		def move(self):
				print("날기")
```