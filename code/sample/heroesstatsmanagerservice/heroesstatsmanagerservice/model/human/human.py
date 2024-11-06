class Human(object):
    def __init__(self, fist_name, eye_color):
        self.first_name = fist_name
        self.eye_color = eye_color
        self.position = 0
    def walk_steps(self, steps):
        self.position += steps