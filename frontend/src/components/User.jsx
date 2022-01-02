import React from 'react'

export function User({user})  {
        return (
          <>
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">{user.name}</h5>
                <h6 class="card-subtitle mb-2 text-muted">{user.email}</h6>
              </div>
            </div>  
          </>
          
        )
    
}
