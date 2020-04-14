from abc import ABC, abstractmethod
class Vector:
    ... # unchanged

class DistanceFactory(ABC):  # abstract class
    def square_rooted(x):
        return round(sqrt(sum[a*a for a in x])), 3)
    
    @abstractmethod
    def compute(self, v1, v2): # abstract method compute()
        pass

class EuclideanFactory(DistanceFactory):  # implement the abstract class
    def compute(self, v1, v2):
        return sqrt(sum(pow(a-b,2) for a,b in zip(v1.value, v2.value)))

class ManhattanFactory(DistanceFactory): # implement the abstract class
    def compute(self, v1, v2):
        return sum(abs(a-b) for a,b in zip(v1.value, v2.value))

class CosineFactory(DistanceFactory): # implement the abstract class
    def compute(self, v1, v2):
        numerator = sum(a*b for a,b in zip(v1.value, v2.value))
        denominator = square_rooted(v1.value)*self.square_rooted(v2.value)
        return round(numerator/float(denominator),3)

class DistanceFinder:
    def __init__(self, factory):
        self.factory = factory  # pass in a concrete instance of DistanceFactory

    def find_distance(self, v1, v2, type):
        distance = factory.compute(v1, v2, type)
        return distance
