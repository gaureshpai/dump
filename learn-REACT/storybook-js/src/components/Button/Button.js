import React from 'react';

const Button = ({children, ...rest}) => {
    <button className='button' {...rest}>{children}</button>
}

export default Button;