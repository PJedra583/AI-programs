import math
lista_list = []
def main(k,trainSet,testSet):
    odczyt(trainSet)
    v_len = len(lista_list[0])-1
    print("Actual accuracy: " + str(accuracy(testSet, k)) + str("%"))
    print("Type 'close' to terminate the program")
    while 1==1 :
        print("Usage: number1;number2 ...")
        a = input()
        if a == 'close':
            break;
        list = a.split(';')
        if len(list)==v_len :
            l_euk = oblicz(list)
            l_args = []
            for i in range(k):
             arg = min(l_euk)
             arg_index = l_euk.index(arg)
             l_euk[arg_index] = float('inf')
             l_args.append(arg_index)
            print(sprawdzenie(nazwanie(l_args),l_args))
        else:
            print("Bad usage")
def odczyt(filename):
    with open(filename, 'r') as file:
        for line in file:
            list = line.strip().split(';') #Ze względu na brak bibl. csv
            lista_list.append(list)
    pass
def oblicz(lista):
    lista_euklides = []
    for i in lista_list:
        x = 0;
        counter = 0;
        for j in lista:
            x += (float(i[counter]) - float(j))**2
            counter += 1
        lista_euklides.append(math.sqrt(x))
    return lista_euklides
def nazwanie(lista):
    map = {}
    for i in lista:
        nazwa = lista_list[i][-1]
        if nazwa not in map:
            map[nazwa] = 0
    return map
def sprawdzenie(map,list) :
    for i in list:
        map[lista_list[i][-1]] +=1
    max = 0;
    max_key = None
    for key,value in map.items():
        if value > max :
            max = value;
            max_key = key
    return max_key

def accuracy(filename, k) :
    lista_list2 = []
    lista_odczyt_nazw = []
    lista_poprawnych_nazw = []
    with open(filename, 'r') as file:
        for line in file:
            list = line.strip().split(';') #Ze względu na brak bibl. csv
            lista_list2.append(list)
            lista_odczyt_nazw.append(list[-1])
    for i in lista_list2 :
        j = i[:-1]
        l_euk = oblicz(j)
        l_args = []
        for l in range(k):
            arg = min(l_euk)
            arg_index = l_euk.index(arg)
            l_euk[arg_index] = float('inf')
            l_args.append(arg_index)
        lista_poprawnych_nazw.append(sprawdzenie(nazwanie(l_args), l_args))
    counter = 0
    for i in range(len(lista_poprawnych_nazw)):
        if lista_poprawnych_nazw[i]==lista_odczyt_nazw[i] :
            counter+=1;
    return counter*100/len(lista_poprawnych_nazw)

if __name__ == "__main__":
    main(2, "train_set.csv", "test_set.csv")
