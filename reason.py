

def main():
    
    def next_states(start_state):
        states = []

        state = start_state
        for q in start_state.keys():
            if q in Ipos.keys() and start_state[q]=='+' and start_state[Ipos[q]]=='0':
                state[Ipos[q]]='+'
            states.append(state)

        state = start_state
        for q in start_state.keys():
            if q in Ipos.keys() and start_state[q]=='+' and start_state[Ipos[q]]=='+':
                state[Ipos[q]]='max'
            states.append(state)

        state = start_state
        for q in start_state.keys():
            if q in Ineg.keys() and start_state[q]!='0':
                if start_state[Ineg[q]]=='+':
                    state[Ipos[q]]='0'
                elif start_state[Ineg[q]]=='max':
                    state[Ipos[q]]='+'
            states.append(state)            

        return states
            
    
    quantities = {'inflow':['0','+'],'outflow':['0','+','max'],'volume':['0','+','max'],
                  'height':['0','+','max'],'pressure':['0','+','max']}

    # dependencies
    Ipos = {'inflow':'volume','volume':'height','height':'pressure'}
    Ineg = {'outflow':'volume'}
    Ppos = {'volume':'outflow'}
    #Ppos = {'volume':'height','height':'pressure','pressure':'outflow'}
    valcor = {'max':('volume','outflow'),'0':('volume','outflow')}
    #valcor = {'max':[('volume','height'),('height','pressure'),('pressure','outflow')],
    #          '0':[('volume','height'),('height','pressure'),('pressure','outflow')]}
    
    # states are represented as dict
    states = next_states({'inflow':'+','outflow':'0','volume':'0','height':'0','pressure':'0'})
    print(states)


    
if __name__ == '__main__':
    main()
