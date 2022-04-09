import pandas as pd
import sys
import matplotlib.pyplot as plt

# Define las funciones de archivo de lectura

def read_data(file_path):
    # COLUMA_NAMES NOMBRE DE TODAS LISTA
    column_names = ['Aparicion', 'config']
    data = pd.read_csv(file_path,header = None, names = column_names)
    return data

# lineal

def simple_line_plot(x,y,figure_no):
    plt.figure(figure_no)
    plt.plot(x,y)
    plt.xlabel('x values')
    plt.ylabel('y values')
    plt.title('simple_line_plot')

# Función de llamada LEER DATOS

dataset = read_data('C:/Users/mrc0l/Documents/Complejos/GoL/core/src/main/java/plots/'+sys.argv[1])
figure_no=1
x=dataset['Aparicion']
y=dataset['config']
figure_no+=1
simple_line_plot(x,y,figure_no)
plt.show()