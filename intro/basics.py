# Basics of using python

# ---- Data types ----
a = 5
print(a, "is of type", type(a))

b = 2.0
print(a, "is of type", type(b))

c = 1+2j
print(c, "is complex number?", isinstance(1+2j,complex))

# ---- List Data Type ----
d = [5,10,15,20,25,30,35,40]

# a[2] = 15
print("a[2] = ", d[2])

# a[0:3] = [5, 10, 15]
print("a[0:3] = ", d[0:3])

# a[5:] = [30, 35, 40]
print("a[5:] = ", d[5:])

# ---- Tuples ----
e = (5,'program', 1+3j)

# t[1] = 'program'
print("t[1] = ", e[1])

# t[0:3] = (5, 'program', (1+3j))
print("t[0:3] = ", e[0:3])

# ---- Strings ----
f = "This is a string"
print(f)

g = '''A multiline
string'''
print(g)

# Concatenating Strings
x = 10
y = 20
print(x + y)

p = "Hello"
q = "World"
print(p + " " + q)

# --- Dictionary ---
h = {1:'value','key':2}
print(type(h))

print("d[1] = ", h[1]);

print("d['key'] = ", h['key']);

# --- Set ---
i = {5,2,3,1,4}

# printing set variable
print("a = ", i)

# data type of variable a
print(type(i))
