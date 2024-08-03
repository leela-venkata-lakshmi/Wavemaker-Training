const display = document.getElementById("display");

function store(button) {
    display.value = display.value + button.value; // Use 'this' keyword which is the button in this context
  }

function clearEnd(button)
{
    display.value=display.value.slice(0,-1);
}

function Clear(button)
{
    display.value="";
}

function signChange(button)
{
    if(display.value[0]=='-')
    {
        display.value=display.value.slice(1);
    }else{
        display.value="-"+display.value;
    }
}

function calculate(button)
{
    let input=display.value;
    let result=0.0;
    let j=0;
    let operator= '+';
    for(let i=0;i<input.length;i++)
    {
        if(isNaN(input[i]) || i===input.length-1)
        {
            let b=parseFloat(input.slice(j,i));
            if(i===input.length-1)
            {
                b=parseFloat(input.slice(j,i+1));
            }
            switch (operator)
            {
                case '+':
                    result= parseFloat(result) + parseFloat(b);
                    break;
                case '-':
                    result= result - b;
                    break;
                case '*':
                    result= result * b;
                    break;
                case '/':
                    result= result / b;
                    break;
                case '%':
                    result= result % b;
                    break;
                case '√':
                    result=Math.sqrt(input.slice(1));
                    break;
            }
            operator=input[i];
            j=i+1;
        }
    }
    // result = Math.round(result * 10) / 10;
    display.value=result;
}

function reciprocal(button)
{
    let input=display.value;
    if(input.includes('/'))
    {
        let index=input.indexOf('/');
        display.value=input.slice(index+1,input.length)/input.slice(0,index);
    }else{
        display.value=1/display.value;
    }

    
}

let memory=0;

function memorySave(button)
{
    memory=parseInt(display.value);
    display.value="Saved to memory";
}
function memoryClear(button){
    memory="";
    display.value="Memory Cleared";
}
function memoryRecall(button)
{
    if(memory!='')
    {
        display.value=memory;
    }
    else{
        display.value="Memory is empty.";
    }
    
}

function addToMemory(button)
{
    memory=memory+parseInt(display.value);
    display.value=memory;
}

function subFromMemory(button)
{
    memory=memory-parseInt(display.value);
    display.value=memory;
}
