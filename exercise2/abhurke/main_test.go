package exercise2

import (
	"testing"

	"github.com/doomsdaypreppers/exercises/exercise1/abhurke/bst"
)

func TestTopViewSimple(t *testing.T) {
	r := bst.Insert(nil, 4)
	bst.Insert(r, 2)
	bst.Insert(r, 3)
	bst.Insert(r, 1)
	bst.Insert(r, 6)
	bst.Insert(r, 5)
	bst.Insert(r, 7)
	var found []uint64
	found = PrintAndGetTopView(r)
	expected := []uint64{1, 2, 4, 6, 7}
	if len(found) != len(expected) {
		t.Errorf("len found: %d, expected: %d", len(found), len(expected))
		t.Fail()
	}
	for i, v := range found {
		if v != expected[i] {
			t.Errorf("found: %d, expected: %d", v, expected[i])
			t.FailNow()
		}
	}
}

func TestTopViewCrooked(t *testing.T) {
	r := bst.Insert(nil, 5)
	bst.Insert(r, 6)
	bst.Insert(r, 1)
	bst.Insert(r, 2)
	bst.Insert(r, 3)
	bst.Insert(r, 4)
	var found []uint64
	found = PrintAndGetTopView(r)
	expected := []uint64{1, 5, 6, 4}
	if len(found) != len(expected) {
		t.Errorf("len found: %d, expected: %d", len(found), len(expected))
		t.Fail()
	}
	for i, v := range found {
		if v != expected[i] {
			t.Errorf("found: %d, expected: %d", v, expected[i])
			t.FailNow()
		}
	}
}
