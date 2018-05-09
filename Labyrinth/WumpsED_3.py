import Tkinter, os, random, pickle
import Image, ImageTk, ImageDraw, ImageFont 

class GUI(Tkinter.Toplevel):
    def __init__(self, root, width, height):
        Tkinter.Toplevel.__init__(self, root)
        self.rows = 12
        self.cols = 12
        self.done = 0
        self.quit = 0
        self.root = root
        self.width = width
        self.height = height
        self.visible = 1
        self.title("PyrobotSimulator: WumpusWorld")
        self.canvas = Tkinter.Canvas(self,width=self.width,height=self.height,bg="white")
        self.canvas.pack()
        self.winfo_toplevel().protocol('WM_DELETE_WINDOW',self.destroy)
        # --------------------------------------------------------
        # Tries to find module folder
        X = __import__(__name__)
        folder = X.__file__
        index = len(folder) - 1
        for i in range(len(folder)):
	    if folder[i] == '/' or folder[i] == '\\':
	        index = i
	folder = folder[:index]
        # --------------------------------------------------------
        self.goldFilename = folder + "/images/gold2.gif" 
        self.wumpusFilename = folder + "/images/wumpus2.gif" 
        self.pitFilename = folder + "/images/pit2.gif"
        self.agentFilename = folder + "/images/agent2.gif" 
        self.doorFilename = folder + "/images/door.gif" 
        self.keyFilename = folder + "/images/key.gif" 
        self.robot_goldFilename = folder + "/images/robot_gold.gif" 
        self.robot_wumpusFilename = folder + "/images/robot_wumpus.gif" 
        self.robot_doorFilename = folder + "/images/robot_door.gif" 
        self.robot_keyFilename = folder + "/images/robot_key.gif" 
        self.exitFilename = folder + "/images/exit.gif" 
        # --------------------------------------------------------
        self.goldImage = Image.open(self.goldFilename)
        self.goldImage = self.goldImage.resize((50, 50), Image.BILINEAR)
        self.wumpusImage = Image.open(self.wumpusFilename)
        self.wumpusImage = self.wumpusImage.resize((50, 50), Image.BILINEAR)
        self.pitImage = Image.open(self.pitFilename)
        self.pitImage = self.pitImage.resize((50, 50), Image.BILINEAR)
        self.agentImage = Image.open(self.agentFilename)
        self.agentImage = self.agentImage.resize((50, 50), Image.BILINEAR)
        self.doorImage = Image.open(self.doorFilename)
        self.doorImage = self.doorImage.resize((50, 50), Image.BILINEAR)
        self.keyImage = Image.open(self.keyFilename)
        self.keyImage = self.keyImage.resize((50, 50), Image.BILINEAR)
        self.robot_goldImage = Image.open(self.robot_goldFilename)
        self.robot_goldImage = self.robot_goldImage.resize((50, 50), Image.BILINEAR)
        self.robot_wumpusImage = Image.open(self.robot_wumpusFilename)
        self.robot_wumpusImage = self.robot_wumpusImage.resize((50, 50), Image.BILINEAR)
        self.robot_doorImage = Image.open(self.robot_doorFilename)
        self.robot_doorImage = self.robot_doorImage.resize((50, 50), Image.BILINEAR)
        self.robot_keyImage = Image.open(self.robot_keyFilename)
        self.robot_keyImage = self.robot_keyImage.resize((50, 50), Image.BILINEAR)
        self.exitImage = Image.open(self.exitFilename)
        self.exitImage = self.exitImage.resize((50, 50), Image.BILINEAR)
        # --------------------------------------------------------
        self.goldImageTk = ImageTk.PhotoImage(self.goldImage)
        self.wumpusImageTk = ImageTk.PhotoImage(self.wumpusImage)
        self.pitImageTk = ImageTk.PhotoImage(self.pitImage)
        self.agentImageTk = ImageTk.PhotoImage(self.agentImage)
        self.doorImageTk = ImageTk.PhotoImage(self.doorImage)
        self.keyImageTk = ImageTk.PhotoImage(self.keyImage)
        self.robot_goldImageTk = ImageTk.PhotoImage(self.robot_goldImage)
        self.robot_wumpusImageTk = ImageTk.PhotoImage(self.robot_wumpusImage)
        self.robot_doorImageTk = ImageTk.PhotoImage(self.robot_doorImage)
        self.robot_keyImageTk = ImageTk.PhotoImage(self.robot_keyImage)
        self.exitImageTk = ImageTk.PhotoImage(self.exitImage)
        self.ticCounter = 0
        # --------------------------------------------------------
        self.properties = ["sonar","location"]
        for i in self.properties:
            self.__dict__[i] = None
        self.initWorld()
        self.checkMovement()
        self.count = 0
        self.tag = "data-%d" % self.count
        self.ports = [60000]
        self.redraw()
    def initWorld(self):
        self.maxTics = 500
        self.location = (1, 1)
        self.x, self.y = self.location
        self.dead = 0
        self.arrow = 1
        self.wumpusDead = 0
        self.gold = 0
        self.world = [['P' for y in range(self.cols)] for x in range(self.rows)]
        # ''  = nothing
        # 'W' = wumpus
        # 'G' = gold
        # 'P' = pit
        # 'A' = agent
        # 'D' = door
        # 'K' = key
        self.world[1][10] = 'G'
        self.world[2][10] = ''
        self.world[6][10] = 'G'
        self.world[9][10] = ''
        self.world[10][10] = 'G'
        self.world[2][9] = ''
        self.world[3][9] = ''
        self.world[4][9] = ''
        self.world[5][9] = ''
        self.world[6][9] = ''
        self.world[7][9] = ''
        self.world[8][9] = ''
        self.world[9][9] = ''
        self.world[1][8] = 'G'
        self.world[2][8] = ''
        self.world[6][8] = ''
        self.world[9][8] = ''
        self.world[10][8] = 'G'
        self.world[1][7] = ''
        self.world[4][7] = 'G'
        self.world[6][7] = 'G'
        self.world[1][6] = ''
        self.world[3][6] = ''
        self.world[4][6] = ''
        self.world[5][6] = ''
        self.world[7][6] = ''
        self.world[8][6] = ''
        self.world[9][6] = 'G'
        self.world[10][6] = ''
        self.world[1][5] = ''
        self.world[5][5] = ''
        self.world[6][5] = ''
        self.world[7][5] = ''
        self.world[10][5] = ''
        self.world[1][4] = ''
        self.world[2][4] = ''
        self.world[3][4] = ''
        self.world[9][4] = ''
        self.world[10][4] = ''
        self.world[3][3] = 'G'
        self.world[7][3] = 'G'
        self.world[8][3] = ''
        self.world[9][3] = ''
        self.world[3][2] = ''
        self.world[4][2] = ''
        self.world[5][2] = 'G'
        self.world[7][2] = ''
        self.world[9][2] = ''
        self.world[1][1] = ''
        self.world[2][1] = 'G'
        self.world[3][1] = ''
        self.world[5][1] = ''
        self.world[6][1] = ''
        self.world[7][1] = ''
        self.world[9][1] = ''
        self.world[10][1] = 'G'
        self.exit_location = (6, 4)
        self.doors_opens = {}
        self.key_values = {}
        self.wumpus_talk = {}
        self.win = False
        self.lose = False
    def add(self, loc, dir):
        x = 0
        if loc[0] + dir[0] >= 0 and loc[0] + dir[0] < self.cols:
            x = loc[0] + dir[0]
        else:
            x = loc[0]
        if loc[1] + dir[1] >= 0 and loc[1] + dir[1] < self.rows:
            y = loc[1] + dir[1]
        else:
            y = loc[1]
        self.location = (x, y)
        self.x, self.y = self.location
    def checkMovement(self):
        value = None
        if self.world[self.location[0]][self.location[1]] == 'W':
            value = 'wumpus'
        elif self.world[self.location[0]][self.location[1]] == 'G':
            value = 'gold'
        elif self.world[self.location[0]][self.location[1]] == 'D':
            value = 'door'
        elif self.world[self.location[0]][self.location[1]] == 'K':
            value = 'key'
        return value
    def inLine(self, loc, change):
        xpos, ypos = self.sum(loc , change)
        while ypos >= 0 and ypos < self.rows and xpos >= 0 and xpos < self.cols:
            if 'W' in self.world[xpos][ypos]:
                self.wumpusDead = 1
                self.world[xpos][ypos] = self.world[xpos][ypos].replace('W', '')
            xpos, ypos = self.sum((xpos,ypos), change)
    def sum(self, a, b):
        return a[0] + b[0], a[1] + b[1]
    def process(self, request, sockname):
        retval = "error"
        if request.count('connectionNum'):
            connectionNum, port = request.split(":")
            retval = self.ports.index( int(port) )
        elif request == 'location':
            retval = (self.location[0] + 1, self.location[1] + 1)
        elif request == 'sonar':
            #retval = (self.world[self.x+1][self.y]!='P',self.world[self.x][self.y-1]!='P',self.world[self.x-1][self.y]!='P',self.world[self.x][self.y+1]!='P')
            retval = {'right' : self.world[self.x+1][self.y]!='P', 'down' : self.world[self.x][self.y-1]!='P', 'left' : self.world[self.x-1][self.y]!='P', 'up' : self.world[self.x][self.y+1]!='P'}
        elif request == 'reset':
            self.initWorld()
            retval = "ok"
            self.win = False
            self.lose = False
            self.ticCounter = 0
            self.redraw()
        elif request == 'win':
            retval = self.win
        elif self.win:
            retval = 'You win!'
        elif self.world[self.location[0]][self.location[1]] == 'E':
            self.win = True
            retval = 'You win!'
        elif self.lose:
            retval = 'Robot batery empty: You lose! (reset to restart)'
        elif request == 'golds':
            retval = sum([self.world[y].count('G') for y in range(self.cols)])
            self.redraw()
        elif request == 'talk':
            if (self.world[self.location[0]][self.location[1]] == 'W'):
                try:
                    retval = self.wumpus_talk[(self.location[0], self.location[1])].pop(0)
                    if len(self.wumpus_talk[(self.location[0], self.location[1])]) == 0:
                        self.world[self.location[0]][self.location[1]] = ''
                except Exception:
                    pass
            elif (self.world[self.location[0]][self.location[1]] == 'K'):
                try:
                    retval = self.key_values[(self.location[0], self.location[1])].pop(0)
                    if len(self.key_values[(self.location[0], self.location[1])]) == 0:
                        self.world[self.location[0]][self.location[1]] = ''
                except Exception:
                    pass
            elif (self.world[self.location[0]][self.location[1]] == 'D'):
                try:
                    if len(self.doors_opens[(self.location[0], self.location[1])][0]) == 0:
                        retval = None
                    else:
                        retval = self.doors_opens[(self.location[0], self.location[1])][0].pop(0)
                except Exception:
                    pass
            else:
                retval = "This thing doesn't speak!"
            self.redraw()
        elif request == 'grab':
            if (self.world[self.location[0]][self.location[1]] == 'G'):
                self.world[self.location[0]][self.location[1]] = ''
                if sum([self.world[y].count('G') for y in range(self.cols)]) == 0:
                    self.world[self.exit_location[0]][self.exit_location[1]] = 'E'
                retval = 'ok'
            else:
                retval = 'no gold to grab'
            self.redraw()
        elif request == 'left':
            if self.ticCounter > self.maxTics:
                self.lose = True
                retval = 'Robot batery empty: You lose! (reset to restart)'
            else:
                try:
                    if self.world[self.location[0] - 1][self.location[1]] != 'P':
                        self.add(self.location, (-1, 0))
                        self.ticCounter += 1
                except Exception:
                    pass
                retval = self.checkMovement()
            self.redraw()
        elif request == 'right':
            if self.ticCounter > self.maxTics:
                self.lose = True
                retval = 'Robot batery empty: You lose! (reset to restart)'
            else:
                try:
                    if self.world[self.location[0] + 1][self.location[1]] != 'P':
                        self.add(self.location, (1, 0))
                        self.ticCounter += 1
                except Exception:
                    pass
                retval = self.checkMovement()
            self.redraw()
        elif request == 'up':
            if self.ticCounter > self.maxTics:
                self.lose = True
                retval = 'Robot batery empty: You lose! (reset to restart)'
            else:
                try:
                    if self.world[self.location[0]][self.location[1] + 1] != 'P':
                        self.add(self.location, (0, 1))
                        self.ticCounter += 1
                except Exception:
                    pass
                retval = self.checkMovement()
            self.redraw()
        elif request == 'down':
            if self.ticCounter > self.maxTics:
                self.lose = True
                retval = 'Robot batery empty: You lose! (reset to restart)'
            else:
                try:
                    if self.world[self.location[0]][self.location[1] - 1] != 'P':
                        self.add(self.location, (0, -1))
                        self.ticCounter += 1
                except Exception:
                    pass
                retval = self.checkMovement()
            self.redraw()
        elif request == 'supportedFeatures':
            retval = []
        elif request == 'builtinDevices':
            retval = []
        else:
            #self.doors_opens = {(6, 8) : [['red', 'blue', 'green', 'pink'], 'currupipi', 3, 8]}
            #self.wumpus_talk = {(3, 6) : ['left', 'right', 'up', 'down'], (8, 3) : ['left', 'right']}
            #self.key_values = {(5, 8) : [currupipi]}
            # Open the door if any
            try:
                hash_list = self.doors_opens[(self.location[0], self.location[1])]
                if request == hash_list[1]:
                    self.world[self.location[0]][self.location[1]] = ''
                    self.world[hash_list[2]][hash_list[3]] = ''
                    retval = 'Opened'
                    self.redraw()
                else:
                    retval = 'Failure'
            except Exception:
                pass
        return pickle.dumps(retval)
    def redraw(self):
        oldtag = self.tag
        self.count = int(not self.count)
        self.tag = "data-%d" % self.count
        for x in range(self.cols):
            for y in range(self.rows):
                posx = x * 50
                posy = ((self.rows - 1) * 50) - y * 50
                if 'P' in self.world[x][y]:
                    self.canvas.create_image(posx, posy, image = self.pitImageTk, anchor=Tkinter.NW,tag=self.tag)
                if 'W' in self.world[x][y]:
                    self.canvas.create_image(posx, posy, image = self.wumpusImageTk, anchor=Tkinter.NW,tag=self.tag)
                if 'G' in self.world[x][y]:
                    self.canvas.create_image(posx, posy, image = self.goldImageTk, anchor=Tkinter.NW,tag=self.tag)
                if 'D' in self.world[x][y]:
                    self.canvas.create_image(posx, posy, image = self.doorImageTk, anchor=Tkinter.NW,tag=self.tag)
                if 'K' in self.world[x][y]:
                    self.canvas.create_image(posx, posy, image = self.keyImageTk, anchor=Tkinter.NW,tag=self.tag)
                if 'E' in self.world[x][y]:
                    self.canvas.create_image(posx, posy, image = self.exitImageTk, anchor=Tkinter.NW,tag=self.tag)
                if self.location[0] == x and self.location[1] == y:
                    if 'G' in self.world[x][y]:
                        self.canvas.create_image(posx, posy, image = self.robot_goldImageTk, anchor=Tkinter.NW,tag=self.tag)
                    elif 'W' in self.world[x][y]:
                        self.canvas.create_image(posx, posy, image = self.robot_wumpusImageTk, anchor=Tkinter.NW,tag=self.tag)
                    elif 'D' in self.world[x][y]:
                        self.canvas.create_image(posx, posy, image = self.robot_doorImageTk, anchor=Tkinter.NW,tag=self.tag)
                    elif 'K' in self.world[x][y]:
                        self.canvas.create_image(posx, posy, image = self.robot_keyImageTk, anchor=Tkinter.NW,tag=self.tag)
                    elif 'E' in self.world[x][y]:
                        self.canvas.create_image(posx, posy, image = self.exitImageTk, anchor=Tkinter.NW,tag=self.tag)
                    else:
                        self.canvas.create_image(posx, posy, image = self.agentImageTk, anchor=Tkinter.NW,tag=self.tag)
        # ------------------------------------------------------------------------        
        for x in range(self.cols):
            px = x * 50
            self.canvas.create_line(px,   0, px, 50 * self.rows, width = 2, fill = "black", tag = self.tag)
        for x in range(self.rows):
            px = x * 50
            self.canvas.create_line(  0, px, 50 * self.cols, px, width = 2, fill = "black", tag = self.tag)
        # ------------------------------------------------------------------------        
        self.canvas.delete(oldtag)
    def destroy(self):
        self.done = 1 # stop processing requests, if handing
        self.quit = 1 # stop accept/bind toplevel
        self.root.quit() # kill the gui
def INIT():
    root = Tkinter.Tk()
    root.withdraw()
    return GUI(root, 600, 600)
