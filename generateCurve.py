import matplotlib
import matplotlib.pyplot as plt

class Curve:
    def __init__(self):
        self.curveX=[]
        self.curveY=[]

    def AddPoint(self,x,y):
        self.curveX.append(x)
        self.curveY.append(y)

    def PrintListXY(self):
        print("X coords: ",self.curveX)
        print("Y coords: ",self.curveY)

    def Plot(self):
        plt.xscale("log")
        plt.plot(self.curveX,self.curveY)
        plt.show()

c=Curve()
c.AddPoint(0.1,60)
c.AddPoint(0.5,15)
c.AddPoint(1,10)
c.AddPoint(5,4.5)
c.AddPoint(10,3)
c.AddPoint(50,2)
c.AddPoint(100,1.8)
c.AddPoint(500,1)
c.AddPoint(1e3,0.9)
c.AddPoint(5e3,1.1)
c.AddPoint(1e4,1.25)
c.AddPoint(1e5,1.30)
c.AddPoint(2e5,1)
c.AddPoint(3.5e5,0.6)
c.AddPoint(5e5,0.2)
c.AddPoint(1e6,0.31)
c.AddPoint(2e6,0.42)
c.AddPoint(5e6,0.6)
c.AddPoint(1e7,0.7)

c.PrintListXY()
c.Plot()