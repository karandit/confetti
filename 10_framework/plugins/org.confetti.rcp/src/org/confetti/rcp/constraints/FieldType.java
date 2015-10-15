package org.confetti.rcp.constraints;


public enum FieldType {
	
    Boolean("boolean-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitBoolean(p1, p2);
		}
    },
    Double("double-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitDouble(p1, p2);
		}
    },
    Integer("integer-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitInteger(p1, p2);
		}
    },
    Day("day-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitDay(p1, p2);
		}
    },
    Hour("hour-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitHour(p1, p2);
		}
    },
    Week("week-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitWeek(p1, p2);
		}
    },
    Period("period-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitPeriod(p1, p2);
		}
    },
    Interval("interval-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitInterval(p1, p2);
		}
    },
    Teacher("teacher-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitTeacher(p1, p2);
		}
    }, 
    StudentGroup("studentgroup-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitStudentGroup(p1, p2);
		}
    },
    Assignment("assignment-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitAssignment(p1, p2);
		}
    },
    AssignmentsSet("assignments-set-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitAssignmentsSet(p1, p2);
		}
    }, 
    AssignmentsCriteria("assignments-criteria-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitAssignmentsCriteria(p1, p2);
		}
    }, 
    Room("room-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitRoom(p1, p2);
		}
    }, 
    RoomsSet("rooms-set-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitRoomsSet(p1, p2);
		}
    }, 
    Subject("subject-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitSubject(p1, p2);
		}
    },
    Tag("tag-field") { //$NON-NLS-1$
		@Override
		public <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2) {
			return visitor.visitTag(p1, p2);
		}
    };

    private String type;
	private FieldType(final String type) {
		this.type = type;
	}
    
	//---------------------- API ---------------------------------------------------------------------------------------
	public static FieldType getByType(String name) {
		for (FieldType fieldType : values()) {
			if (fieldType.type.equals(name)) {
				return fieldType;
			}
		}
		return null;
	}

    public abstract <R, P1, P2> R accept(FieldTypeVisitor<R, P1, P2> visitor, P1 p1, P2 p2); 
    
}
