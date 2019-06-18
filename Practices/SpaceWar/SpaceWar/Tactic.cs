using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceWar
{
    interface Tactic
    {
        void Attack(LocatableObject obj);
        void Retreat(LocatableObject obj);
    }
}
