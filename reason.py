

def main():
    inflow = ''
    outflow = ''
    volume = ''
    height = ''
    pressure = ''

    
    quantities = {'inflow':['0','+'],'outflow':['0','+','max'],'volume':['0','+','max'],
                  'height':['0','+','max'],'pressure':['0',+,'max']}

    # dependencies
    Ipos = [('inflow','volume'),('volume','height'),('height','pressure')]
    Ineg = [('outflow','volume')]
    Ppos = [('volume','outflow')]
    valcor = [(volume=='max',height=='max'), (volume=='0', height=='0'),
              (height=='max',pressure=='max'), (height=='0',pressure=='0'),
              (pressure=='max',outflow=='max'),(pressure=='0',outflow=='0')]
    
    # states are represented as lists of values representing
    [inflow,outflow,volume,height,pressure]
    start_state = ['+','0','0','0','0']
    
def next_state(state):
    

    
if __name__ == '__main__':
    main()
